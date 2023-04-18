package by.kozlov.jdbc.starter.servlet;

import by.kozlov.jdbc.starter.service.ProductionService;
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
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        Integer workerId = Integer.valueOf(req.getParameter("workerId"));
        req.setAttribute("sets",productionService.findAllByWorkerId(workerId));
        req.getRequestDispatcher("WEB-INF/jsp/production.jsp").forward(req,resp);
    }
}
