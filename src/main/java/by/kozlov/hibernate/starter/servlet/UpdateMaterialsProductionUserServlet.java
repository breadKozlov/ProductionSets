package by.kozlov.hibernate.starter.servlet;

import by.kozlov.hibernate.starter.dto.UpdateMaterialsProductionDto;
import by.kozlov.hibernate.starter.dto.WorkerDto;
import by.kozlov.hibernate.starter.service.MaterialService;
import by.kozlov.hibernate.starter.service.MaterialsProductionService;
import by.kozlov.hibernate.starter.utils.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.validation.ConstraintViolationException;
import java.io.IOException;

@WebServlet("/updateMaterialsProductionUser")
public class UpdateMaterialsProductionUserServlet extends HttpServlet {

    private final MaterialService materialService = MaterialService.getInstance();
    private final MaterialsProductionService materialsProductionService = MaterialsProductionService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var materials = materialService.findAll();
        var idProduction = Integer.parseInt(req.getParameter("id"));
        req.setAttribute("materials",materials);
        req.setAttribute("id",idProduction);
        req.getRequestDispatcher(JspHelper.getPath("updateMaterialsProductionUser")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var idBrigade = ((WorkerDto) req.getSession().getAttribute("worker")).getBrigade().getId();
        var materialsProduction = UpdateMaterialsProductionDto.builder()
                .id(req.getParameter("id"))
                .material(req.getParameter("materialId"))
                .brigade(String.valueOf(idBrigade))
                .quantity(req.getParameter("quantity"))
                .dateOfProduction(req.getParameter("dateOfProduction"))
                .build();

        try {
            materialsProductionService.update(materialsProduction);
            resp.sendRedirect("./materialsProductionUser");
        } catch (ConstraintViolationException exception) {
            req.setAttribute("errors", exception.getConstraintViolations());
            req.setAttribute("id",req.getParameter("id"));
            doGet(req,resp);
        }
    }
}
