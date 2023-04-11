package by.kozlov.jdbc.starter.servlet;

import by.kozlov.jdbc.starter.service.WorkerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/hello")
public class WorkerServlet extends HttpServlet {

    private static final WorkerService workerService = WorkerService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        try (var writer = resp.getWriter()) {
            writer.write("<h1>Workers: </h1>");
            writer.write("<ul>");
            workerService.findAll().stream().forEach(workerDto -> {
                        writer.write("""
                                    <li>
                                    <a href='/worker?workerId=%d'>%s</a>
                                    </li>
                                """.formatted(workerDto.id(), workerDto.description()));
                    }
            );
            writer.write("</ul>");
        }

    }
}
