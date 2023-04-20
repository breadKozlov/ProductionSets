package by.kozlov.jdbc.starter.servlet;

import by.kozlov.jdbc.starter.dto.UserDto;
import by.kozlov.jdbc.starter.service.ProductionService;
import by.kozlov.jdbc.starter.service.WorkerService;
import by.kozlov.jdbc.starter.service.WorkersSetsService;
import by.kozlov.jdbc.starter.utils.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    private WorkerService workerService = WorkerService.getInstance();
    private WorkersSetsService workersSetsService = WorkersSetsService.getInstance();
    private ProductionService productionService = ProductionService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var user = ((UserDto) req.getSession().getAttribute("user"));
        var email = user.getEmail();
        var worker = workerService.findByEmail(email).orElseThrow();
        var description = worker.getNameOfWorker() + " " + worker.getSurnameOfWorker() + " - " + worker.getBrigade();
        var workersSets = workersSetsService.findAllByWorkerId(worker.getId());
        var releasedSets = productionService.findAllByWorkerId(worker.getId());

        req.setAttribute("description",description);
        req.setAttribute("workersSets",workersSets);
        req.setAttribute("releasedSets",releasedSets);
        req.getRequestDispatcher(JspHelper.getPath("user"))
                .forward(req, resp);
    }
}
