package by.kozlov.jdbc.starter.servlet;

import by.kozlov.jdbc.starter.service.ProductionService;
import by.kozlov.jdbc.starter.utils.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/production")
public class ProductionServlet extends HttpServlet {

    private ProductionService productionService = ProductionService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");

        if (req.getParameter("workerId") != null) {
            Integer workerId = Integer.valueOf(req.getParameter("workerId"));
            req.setAttribute("sets",productionService.findAllByWorkerId(workerId));
        }
        req.setAttribute("productions",productionService.findAll());
        req.getRequestDispatcher(JspHelper.getPath("production"))
                .forward(req,resp);
    }
}
