package by.kozlov.hibernate.starter.servlet;

import by.kozlov.hibernate.starter.dto.UpdateProductionDto;
import by.kozlov.hibernate.starter.service.ProductionService;
import by.kozlov.hibernate.starter.service.SetService;
import by.kozlov.hibernate.starter.utils.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.validation.ConstraintViolationException;
import java.io.IOException;

@WebServlet("/updateUserProductionAdmin")
public class UpdateUserProductionAdminServlet extends HttpServlet {

    private final SetService setService = SetService.getInstance();
    private final ProductionService productionService = ProductionService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var sets = setService.findAll();
        var idProduction = Integer.parseInt(req.getParameter("id"));
        var workerSet = productionService.findById(idProduction).orElseThrow();
        req.setAttribute("sets",sets);
        req.setAttribute("workerSet",workerSet);
        req.setAttribute("id",idProduction);
        req.getRequestDispatcher(JspHelper.getPath("updateUserProductionAdmin")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var idWorker = req.getSession().getAttribute("id");
        var productDto = UpdateProductionDto.builder()
                .id(req.getParameter("id"))
                .worker(String.valueOf(idWorker))
                .set(req.getParameter("setId"))
                .madeSets(req.getParameter("madeSets"))
                .dateOfProduction(req.getParameter("dateOfProduction"))
                .build();
        try {
            productionService.update(productDto);
            resp.sendRedirect("./productionEveryUser");
        } catch (ConstraintViolationException exception) {
            req.setAttribute("errors", exception.getConstraintViolations());
            req.setAttribute("id",req.getParameter("id"));
            doGet(req, resp);
        }
    }
}
