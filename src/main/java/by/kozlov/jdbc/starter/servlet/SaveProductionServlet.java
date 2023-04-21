package by.kozlov.jdbc.starter.servlet;


import by.kozlov.jdbc.starter.dto.CreateProductionDto;
import by.kozlov.jdbc.starter.dto.CreateUserDto;
import by.kozlov.jdbc.starter.exception.ValidationException;
import by.kozlov.jdbc.starter.service.ProductionService;
import by.kozlov.jdbc.starter.service.SetService;
import by.kozlov.jdbc.starter.service.WorkerService;
import by.kozlov.jdbc.starter.utils.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/saveProduction")
public class SaveProductionServlet extends HttpServlet {

    private final SetService setService = SetService.getInstance();
    private final WorkerService workerService = WorkerService.getInstance();
    private final ProductionService productionService = ProductionService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var sets = setService.findAll();
        var workers = workerService.findAll();
        req.setAttribute("sets",sets);
        req.setAttribute("workers",workers);
        req.getRequestDispatcher(JspHelper.getPath("saveProduction")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var productDto = CreateProductionDto.builder()
                .worker(req.getParameter("workerId"))
                .set(req.getParameter("setId"))
                .madeSets(req.getParameter("madeSets"))
                .dateOfProduction(req.getParameter("dateOfProduction"))
                .build();

        try {
            productionService.create(productDto);
            resp.sendRedirect("./production");
        } catch (ValidationException exception) {
            req.setAttribute("errors", exception.getErrors());
            doGet(req, resp);
        }

    }
}
