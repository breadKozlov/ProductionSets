package by.kozlov.jdbc.starter.servlet;

import by.kozlov.jdbc.starter.service.WorkerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

    private static final WorkerService workerService = WorkerService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        try (var writer = resp.getWriter()) {
            writer.write("<html><body><h1>Workers: </h1>");
            writer.write("<ul>");
            workerService.findAll().stream().forEach(workerDto -> {
                        writer.write("""
                                    <li>
                                    <a href='./production?workerId=%d'>%s</a>
                                    </li>
                                """.formatted(workerDto.getId(), workerDto.getDescription()));
                    }
            );
            writer.write("</ul></br>");
            writer.write("<a href='./production'>Production</a></br>");
            writer.write("<a href='./requirement'>Requirement</a></br>");
            writer.write("<a href='./materialsProduction'>Materials production</a></body></html>");
        }
    }
}
