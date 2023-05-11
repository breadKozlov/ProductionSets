package by.kozlov.hibernate.starter.servlet;

import by.kozlov.hibernate.starter.service.DifferenceService;
import by.kozlov.hibernate.starter.utils.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/planProduction")
public class PlanProductionServlet extends HttpServlet {

    private final DifferenceService differenceMaterialsService = DifferenceService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setAttribute("productions",differenceMaterialsService.findAllDifferenceProductionMaterials());
        req.getRequestDispatcher(JspHelper.getPath("planProduction"))
                .forward(req,resp);
    }
}
