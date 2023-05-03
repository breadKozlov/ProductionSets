package by.kozlov.jdbc.starter.servlet;

import by.kozlov.jdbc.starter.dto.WorkerDto;
import by.kozlov.jdbc.starter.service.BrigadeService;
import by.kozlov.jdbc.starter.service.MaterialsProductionService;
import by.kozlov.jdbc.starter.utils.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/materialsProductionUser")
public class MaterialsProductionUserServlet extends HttpServlet {

    private final BrigadeService brigadeService = BrigadeService.getInstance();
    private final MaterialsProductionService materialsProductionService = MaterialsProductionService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        var idBrigade = ((WorkerDto) req.getSession().getAttribute("worker")).getBrigade().getId();
        req.setAttribute("brigade",brigadeService.findById(idBrigade).orElseThrow());
        req.setAttribute("materials",materialsProductionService.findAllByBrigadeId(idBrigade));
        req.getRequestDispatcher(JspHelper.getPath("productionMaterialsUser"))
                .forward(req, resp);
    }
}
