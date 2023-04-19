package by.kozlov.jdbc.starter.servlet;


import by.kozlov.jdbc.starter.service.RequirementService;
import by.kozlov.jdbc.starter.utils.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/requirement")
public class RequirementServlet extends HttpServlet {

    private final RequirementService requirementService = RequirementService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        req.setAttribute("requirements",requirementService.findAll());
        req.getRequestDispatcher(JspHelper.getPath("requirement"))
                .forward(req, resp);
    }
}
