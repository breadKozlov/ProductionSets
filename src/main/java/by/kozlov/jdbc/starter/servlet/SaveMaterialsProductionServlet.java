package by.kozlov.jdbc.starter.servlet;

import by.kozlov.jdbc.starter.service.BrigadeService;
import by.kozlov.jdbc.starter.service.MaterialService;
import by.kozlov.jdbc.starter.utils.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/saveMaterialsProduction")
public class SaveMaterialsProductionServlet extends HttpServlet {

    private final BrigadeService brigadeService = BrigadeService.getInstance();
    private final MaterialService materialService = MaterialService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var brigades = brigadeService.findAll();
        var materials = materialService.findAll();
        req.setAttribute("brigades",brigades);
        req.setAttribute("materials",materials);
        if (req.getParameter("id") != null) {
            req.getRequestDispatcher(JspHelper.getPath("saveMaterialsProductionUser")).forward(req, resp);
        } else  {
            req.getRequestDispatcher(JspHelper.getPath("saveMaterialsProductionAdmin")).forward(req, resp);
        }
    }
}
