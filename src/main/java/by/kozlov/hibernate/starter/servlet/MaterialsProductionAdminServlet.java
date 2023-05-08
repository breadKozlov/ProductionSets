package by.kozlov.hibernate.starter.servlet;

import by.kozlov.hibernate.starter.service.MaterialsProductionService;
import by.kozlov.hibernate.starter.utils.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/materialsProductionAdmin")
public class MaterialsProductionAdminServlet extends HttpServlet {

    private final MaterialsProductionService materialsProductionService = MaterialsProductionService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setAttribute("materials",materialsProductionService.findAll());
        req.getRequestDispatcher(JspHelper.getPath("productionMaterialsAdmin"))
                .forward(req, resp);
    }
}
