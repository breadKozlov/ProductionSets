package by.kozlov.hibernate.starter.servlet;

import by.kozlov.hibernate.starter.dto.CreateProductionDto;
import by.kozlov.hibernate.starter.exception.ValidationException;
import by.kozlov.hibernate.starter.service.ProductionService;
import by.kozlov.hibernate.starter.service.SetService;
import by.kozlov.hibernate.starter.utils.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/saveUserProductionAdmin")
public class SaveUserProductionAdminServlet extends HttpServlet {

    private final SetService setService = SetService.getInstance();
    private final ProductionService productionService = ProductionService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var sets = setService.findAll();
        req.setAttribute("sets",sets);
        req.getRequestDispatcher(JspHelper.getPath("saveUserProductionAdmin")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var id = req.getSession().getAttribute("id");
        var productDto = CreateProductionDto.builder()
                .worker((String) id)
                .set(req.getParameter("setId"))
                .madeSets(req.getParameter("madeSets"))
                .dateOfProduction(req.getParameter("dateOfProduction"))
                .build();

        try {
            productionService.create(productDto);
            resp.sendRedirect("./productionEveryUser");
        } catch (ValidationException exception) {
            req.setAttribute("errors", exception.getErrors());
            doGet(req, resp);
        }
    }
}
