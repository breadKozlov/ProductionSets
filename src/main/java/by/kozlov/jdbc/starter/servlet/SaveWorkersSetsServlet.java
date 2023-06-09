package by.kozlov.jdbc.starter.servlet;

import by.kozlov.jdbc.starter.dto.CreateProductionDto;
import by.kozlov.jdbc.starter.dto.CreateWorkersSetsDto;
import by.kozlov.jdbc.starter.dto.WorkerDto;
import by.kozlov.jdbc.starter.dto.WorkersSetsDto;
import by.kozlov.jdbc.starter.exception.ValidationException;
import by.kozlov.jdbc.starter.service.SetService;
import by.kozlov.jdbc.starter.service.WorkersSetsService;
import by.kozlov.jdbc.starter.utils.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/saveWorkersSets")
public class SaveWorkersSetsServlet extends HttpServlet {

    private final SetService setService = SetService.getInstance();
    private final WorkersSetsService workersSetsService = WorkersSetsService.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var id = ((WorkerDto) req.getSession().getAttribute("worker")).getId();
        var workerSets = workersSetsService.findAllByWorkerId(id);
        var freeSets = setService.findAllFreeSets(workerSets);
        req.setAttribute("sets",freeSets);

        req.getRequestDispatcher(JspHelper.getPath("saveWorkersSets")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var id = ((WorkerDto) req.getSession().getAttribute("worker")).getId();
        var workersSetsDto = CreateWorkersSetsDto.builder()
                .worker(String.valueOf(id))
                .set(req.getParameter("setId"))
                .requirement(req.getParameter("requirement"))
                .build();

        try {
            workersSetsService.create(workersSetsDto);
            resp.sendRedirect("./user");
        } catch (ValidationException exception) {
            req.setAttribute("errors", exception.getErrors());
            doGet(req, resp);
        }
    }
}
