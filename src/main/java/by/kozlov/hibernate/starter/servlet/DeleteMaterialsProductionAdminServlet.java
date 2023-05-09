package by.kozlov.hibernate.starter.servlet;

import by.kozlov.hibernate.starter.service.MaterialsProductionService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/deleteMaterialsProductionAdmin")
public class DeleteMaterialsProductionAdminServlet extends HttpServlet {

    private final MaterialsProductionService materialsProductionService = MaterialsProductionService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (materialsProductionService.delete(Integer.parseInt(req.getParameter("id")))) {
            resp.sendRedirect("./materialsProductionAdmin");
        } else {
            req.setAttribute("message", "Sorry incorrect id. Retry please");
            getServletContext().getRequestDispatcher("error").forward(req, resp);
        }
    }
}
