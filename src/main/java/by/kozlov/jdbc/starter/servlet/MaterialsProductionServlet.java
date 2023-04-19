package by.kozlov.jdbc.starter.servlet;

import by.kozlov.jdbc.starter.service.MaterialsProductionService;
import by.kozlov.jdbc.starter.utils.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/materialsProduction")
public class MaterialsProductionServlet extends HttpServlet {

    private MaterialsProductionService materialsProductionService = MaterialsProductionService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setAttribute("materials",materialsProductionService.findAll());
        req.getRequestDispatcher(JspHelper.getPath("productionMaterials"))
                .forward(req, resp);
    }
}
