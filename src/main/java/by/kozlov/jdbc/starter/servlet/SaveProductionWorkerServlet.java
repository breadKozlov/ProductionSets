package by.kozlov.jdbc.starter.servlet;

import by.kozlov.jdbc.starter.dto.CreateProductionDto;
import by.kozlov.jdbc.starter.dto.WorkerDto;
import by.kozlov.jdbc.starter.exception.ValidationException;
import by.kozlov.jdbc.starter.service.ProductionService;
import by.kozlov.jdbc.starter.service.SetService;
import by.kozlov.jdbc.starter.service.WorkerService;
import by.kozlov.jdbc.starter.utils.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/saveProductionWorker")
public class SaveProductionWorkerServlet extends HttpServlet {

    private final SetService setService = SetService.getInstance();
    private final ProductionService productionService = ProductionService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var sets = setService.findAll();
        req.setAttribute("sets",sets);
        var id = ((WorkerDto) req.getSession().getAttribute("worker")).getId();
        req.setAttribute("id",id);
        req.getRequestDispatcher(JspHelper.getPath("saveProductionWorker")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var id = ((WorkerDto) req.getSession().getAttribute("worker")).getId();
        var productDto = CreateProductionDto.builder()
                .worker(String.valueOf(id))
                .set(req.getParameter("setId"))
                .madeSets(req.getParameter("madeSets"))
                .dateOfProduction(req.getParameter("dateOfProduction"))
                .build();

        try {
            productionService.create(productDto);
            resp.sendRedirect("./user");
        } catch (ValidationException exception) {
            req.setAttribute("errors", exception.getErrors());
            doGet(req, resp);
        }

    }
}

