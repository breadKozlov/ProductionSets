package by.kozlov.jdbc.starter.servlet;

import by.kozlov.jdbc.starter.dto.CreateMaterialsProductionDto;
import by.kozlov.jdbc.starter.dto.WorkerDto;
import by.kozlov.jdbc.starter.exception.ValidationException;
import by.kozlov.jdbc.starter.service.MaterialService;
import by.kozlov.jdbc.starter.service.MaterialsProductionService;
import by.kozlov.jdbc.starter.utils.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/saveMaterialsProductionUser")
public class SaveMaterialsProductionUserServlet extends HttpServlet {

    private final MaterialService materialService = MaterialService.getInstance();
    private final MaterialsProductionService materialsProductionService = MaterialsProductionService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var materials = materialService.findAll();
        req.setAttribute("materials",materials);
        req.getRequestDispatcher(JspHelper.getPath("saveMaterialsProductionUser")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var idBrigade = ((WorkerDto) req.getSession().getAttribute("worker")).getBrigade().getId();
        var materialsProduction = CreateMaterialsProductionDto.builder()
                .material(req.getParameter("materialId"))
                .brigade(String.valueOf(idBrigade))
                .quantity(req.getParameter("quantity"))
                .dateOfProduction(req.getParameter("dateOfProduction"))
                .build();

        try {
            materialsProductionService.create(materialsProduction);
            resp.sendRedirect("./materialsProductionUser");
        } catch (ValidationException exception) {
            req.setAttribute("errors",exception.getErrors());
            doGet(req,resp);
        }
    }
}
