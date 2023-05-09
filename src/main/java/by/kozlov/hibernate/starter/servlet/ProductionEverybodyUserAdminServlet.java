package by.kozlov.hibernate.starter.servlet;

import by.kozlov.hibernate.starter.service.ProductionService;
import by.kozlov.hibernate.starter.utils.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/productionEveryUser")
public class ProductionEverybodyUserAdminServlet extends HttpServlet {

    private final ProductionService productionService = ProductionService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("id") == null) {
            req.getSession().setAttribute("id",req.getParameter("id"));
        }
        var id = Integer.parseInt((String) req.getSession().getAttribute("id"));
        req.setAttribute("sets",productionService.findAllByWorkerId(id));
        req.getRequestDispatcher(JspHelper.getPath("productionEveryUserAdmin"))
                .forward(req,resp);
    }
}
