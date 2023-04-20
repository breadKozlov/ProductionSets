package by.kozlov.jdbc.starter.servlet;


import by.kozlov.jdbc.starter.service.SetService;
import by.kozlov.jdbc.starter.service.WorkerService;
import by.kozlov.jdbc.starter.utils.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet("/saveProduction")
public class SaveProductionServlet extends HttpServlet {

    private final SetService setService = SetService.getInstance();
    private final WorkerService workerService = WorkerService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var sets = setService.findAll();
        var workers = workerService.findAll();
        req.setAttribute("sets",sets);
        req.setAttribute("workers",workers);
        req.getRequestDispatcher(JspHelper.getPath("saveProduction")).forward(req, resp);
    }
}
