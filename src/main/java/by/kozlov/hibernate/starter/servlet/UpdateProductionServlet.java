package by.kozlov.hibernate.starter.servlet;

import by.kozlov.hibernate.starter.exception.ValidationException;
import by.kozlov.hibernate.starter.dto.UpdateProductionDto;
import by.kozlov.hibernate.starter.service.ProductionService;
import by.kozlov.hibernate.starter.service.SetService;
import by.kozlov.hibernate.starter.service.WorkerService;
import by.kozlov.hibernate.starter.utils.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/updateProduction")
public class UpdateProductionServlet extends HttpServlet {

    private final ProductionService productionService = ProductionService.getInstance();
    private final SetService setService = SetService.getInstance();
    private final WorkerService workerService = WorkerService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var sets = setService.findAll();
        var workers = workerService.findAll();
        var idProduction = Integer.parseInt(req.getParameter("id"));
        var workerSet = productionService.findById(idProduction)
                .orElseThrow();
        req.setAttribute("sets",sets);
        req.setAttribute("workers",workers);
        req.setAttribute("workerSet",workerSet);
        req.setAttribute("id",idProduction);
        req.getRequestDispatcher(JspHelper.getPath("updateProduction")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var productDto = UpdateProductionDto.builder()
                .id(req.getParameter("id"))
                .worker(req.getParameter("workerId"))
                .set(req.getParameter("setId"))
                .madeSets(req.getParameter("madeSets"))
                .dateOfProduction(req.getParameter("dateOfProduction"))
                .build();
        try {
            productionService.update(productDto);
            resp.sendRedirect("./production");
        } catch (ValidationException exception) {
            req.setAttribute("errors", exception.getErrors());
            req.setAttribute("id",req.getParameter("id"));
            doGet(req, resp);
        }
    }
}
