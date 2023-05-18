package by.kozlov.hibernate.starter.servlet;

import by.kozlov.hibernate.starter.dto.UserDto;
import by.kozlov.hibernate.starter.service.*;
import by.kozlov.hibernate.starter.utils.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    private final WorkerService workerService = WorkerService.getInstance();
    private final WorkersSetsService workersSetsService = WorkersSetsService.getInstance();
    private final ProductionService productionService = ProductionService.getInstance();

    private final DifferenceService differenceService = DifferenceService.getInstance();
    private final MaterialsProductionService materialsProductionService = MaterialsProductionService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var user = ((UserDto) req.getSession().getAttribute("user"));
        var email = user.getEmail();
        var worker = workerService.findByEmail(email).orElseThrow();
        var description = worker.getNameOfWorker() + " " + worker.getSurnameOfWorker() + " - "
                + worker.getBrigade().getNameOfBrigade() + ", " + worker.getBrigade().getPhoneNumberOfForeman();
        var workersSets = workersSetsService.findAllByWorkerId(worker.getId());
        var releasedSets = productionService.findAllByWorkerId(worker.getId());

        if (worker.getSpeciality().equals("extruder foreman")) {
            req.getSession().setAttribute("foreman","true");
        }
        req.getSession().setAttribute("worker",worker);
        req.setAttribute("id",worker.getId());
        req.setAttribute("description",description);
        req.setAttribute("workersSets",workersSets);
        req.setAttribute("releasedSets",releasedSets);
        req.setAttribute("sets",differenceService.findAllDifferenceWorkerSets(worker.getId()));
        req.getRequestDispatcher(JspHelper.getPath("user"))
                .forward(req, resp);
    }
}
