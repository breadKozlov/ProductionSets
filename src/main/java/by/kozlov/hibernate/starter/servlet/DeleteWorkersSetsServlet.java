package by.kozlov.hibernate.starter.servlet;

import by.kozlov.hibernate.starter.service.WorkersSetsService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/deleteWorkersSets")
public class DeleteWorkersSetsServlet extends HttpServlet {

    private final WorkersSetsService workersSetsService = WorkersSetsService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (workersSetsService.delete(Integer.parseInt(req.getParameter("id")))) {
            resp.sendRedirect("./user");
        } else {
            req.setAttribute("message", "Sorry incorrect id. Retry please");
            getServletContext().getRequestDispatcher("error").forward(req, resp);
        }
    }


}
