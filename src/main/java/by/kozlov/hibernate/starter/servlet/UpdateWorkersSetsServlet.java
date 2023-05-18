package by.kozlov.hibernate.starter.servlet;

import by.kozlov.hibernate.starter.dto.UpdateWorkersSetsDto;
import by.kozlov.hibernate.starter.dto.WorkerDto;
import by.kozlov.hibernate.starter.service.SetService;
import by.kozlov.hibernate.starter.service.WorkersSetsService;
import by.kozlov.hibernate.starter.utils.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.validation.ConstraintViolationException;
import java.io.IOException;

@WebServlet("/updateWorkersSets")
public class UpdateWorkersSetsServlet extends HttpServlet {

    private final SetService setService = SetService.getInstance();
    private final WorkersSetsService workersSetsService = WorkersSetsService.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var worker = ((WorkerDto) req.getSession().getAttribute("worker"));
        var id = worker.getId();
        var workerSets = workersSetsService.findAllByWorkerId(id);
        var currentWorkerSetId = workersSetsService.findById(Integer.parseInt(req.getParameter("id"))).orElseThrow()
                .getSet().getId();
        var currentSetDto = setService.findById(currentWorkerSetId).orElseThrow();//добавил текущий комплект в список комплектов для ребактирования
        var freeSets = setService.findAllFreeSets(workerSets);
        freeSets.add(currentSetDto);
        req.setAttribute("id",req.getParameter("id"));
        req.setAttribute("sets",freeSets);
        req.getRequestDispatcher(JspHelper.getPath("updateWorkersSets")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var id = ((WorkerDto) req.getSession().getAttribute("worker")).getId();
        var workersSetsDto = UpdateWorkersSetsDto.builder()
                .id(req.getParameter("id"))
                .worker(String.valueOf(id))
                .set(req.getParameter("setId"))
                .requirement(req.getParameter("requirement"))
                .build();

        try {
            workersSetsService.update(workersSetsDto);
            resp.sendRedirect("./user");
        } catch (ConstraintViolationException exception) {
            req.setAttribute("errors", exception.getConstraintViolations());
            req.setAttribute("id",req.getParameter("id"));
            doGet(req, resp);
        }
    }
}
