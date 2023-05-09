package by.kozlov.hibernate.starter.servlet;

import by.kozlov.hibernate.starter.dto.UpdateProductionDto;
import by.kozlov.hibernate.starter.dto.UpdateRequirementDto;
import by.kozlov.hibernate.starter.exception.ValidationException;
import by.kozlov.hibernate.starter.service.RequirementService;
import by.kozlov.hibernate.starter.utils.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/updateRequirement")
public class UpdateRequirementServlet extends HttpServlet {

    private final RequirementService requirementService = RequirementService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("id",req.getParameter("id"));
        req.getRequestDispatcher(JspHelper.getPath("updateRequirement")).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var currentRequirement = requirementService.findById(Integer.parseInt(req.getParameter("id"))).orElseThrow();
        var requirementDto = UpdateRequirementDto.builder()
                .id(req.getParameter("id"))
                .set(String.valueOf(currentRequirement.getSet().getId()))
                .material(String.valueOf(currentRequirement.getMaterial().getId()))
                .unitCost(String.valueOf(currentRequirement.getUnitCost()))
                .totalSets(req.getParameter("totalSets"))
                .build();
        try {
            requirementService.update(requirementDto);
            resp.sendRedirect("./requirement");
        } catch (ValidationException exception) {
            req.setAttribute("errors", exception.getErrors());
            req.setAttribute("id",req.getParameter("id"));
            doGet(req, resp);
        }
    }
}
