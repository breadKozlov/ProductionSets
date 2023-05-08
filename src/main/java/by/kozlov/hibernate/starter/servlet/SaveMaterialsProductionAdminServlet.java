package by.kozlov.hibernate.starter.servlet;

import by.kozlov.hibernate.starter.dto.CreateMaterialsProductionDto;
import by.kozlov.hibernate.starter.dto.WorkerDto;
import by.kozlov.hibernate.starter.exception.ValidationException;
import by.kozlov.hibernate.starter.service.BrigadeService;
import by.kozlov.hibernate.starter.service.MaterialService;
import by.kozlov.hibernate.starter.service.MaterialsProductionService;
import by.kozlov.hibernate.starter.utils.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/saveMaterialsProductionAdmin")
public class SaveMaterialsProductionAdminServlet extends HttpServlet {

    private final BrigadeService brigadeService = BrigadeService.getInstance();
    private final MaterialService materialService = MaterialService.getInstance();
    private final MaterialsProductionService materialsProductionService = MaterialsProductionService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var brigades = brigadeService.findAll();
        var materials = materialService.findAll();
        req.setAttribute("brigades",brigades);
        req.setAttribute("materials",materials);
        req.getRequestDispatcher(JspHelper.getPath("saveMaterialsProductionAdmin")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var materialsProduction = CreateMaterialsProductionDto.builder()
                .material(req.getParameter("materialId"))
                .brigade(req.getParameter("brigadeId"))
                .quantity(req.getParameter("quantity"))
                .dateOfProduction(req.getParameter("dateOfProduction"))
                .build();

        try {
            materialsProductionService.create(materialsProduction);
            resp.sendRedirect("./materialsProductionAdmin");
        } catch (ValidationException exception) {
            req.setAttribute("errors",exception.getErrors());
            doGet(req,resp);
        }
    }
}
