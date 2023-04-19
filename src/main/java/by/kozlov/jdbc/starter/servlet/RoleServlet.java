package by.kozlov.jdbc.starter.servlet;

import by.kozlov.jdbc.starter.dto.UserDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;

@WebServlet("/role")
public class RoleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var user = ((UserDto) req.getSession().getAttribute("user"));
        var role = user.getRole().name();
        if ("ADMIN".equals(role)) {
                onRoleAdmin(user,req,resp);
        } else {
            onRoleUser(user,req,resp);
        }

    }

    @SneakyThrows
    private void onRoleUser(UserDto user, HttpServletRequest req, HttpServletResponse resp) {

        resp.sendRedirect("./user");
    }

    @SneakyThrows
    private void onRoleAdmin(UserDto user, HttpServletRequest req, HttpServletResponse resp) {

        resp.sendRedirect("./admin");
    }
}
