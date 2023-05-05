package by.kozlov.hibernate.starter.servlet;


import by.kozlov.hibernate.starter.service.RequirementService;
import by.kozlov.hibernate.starter.utils.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/requirement")
public class RequirementServlet extends HttpServlet {

    private final RequirementService requirementService = RequirementService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        req.setAttribute("requirements",requirementService.findAll());
        req.getRequestDispatcher(JspHelper.getPath("requirement"))
                .forward(req, resp);
    }
}
