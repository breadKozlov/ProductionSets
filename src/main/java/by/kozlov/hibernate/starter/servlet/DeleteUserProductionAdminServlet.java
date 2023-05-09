package by.kozlov.hibernate.starter.servlet;

import by.kozlov.hibernate.starter.service.ProductionService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/deleteUserProductionAdmin")
public class DeleteUserProductionAdminServlet extends HttpServlet {
    private final ProductionService productionService = ProductionService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (productionService.delete(Integer.parseInt(req.getParameter("id")))) {
            resp.sendRedirect("./productionEveryUser");
        } else {
            req.setAttribute("message", "Sorry incorrect id. Retry please");
            getServletContext().getRequestDispatcher("error").forward(req, resp);
        }
    }

}
