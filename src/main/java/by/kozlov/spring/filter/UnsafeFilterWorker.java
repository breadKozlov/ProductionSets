package by.kozlov.spring.filter;

import by.kozlov.spring.dto.UserReadDto;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(urlPatterns = "/worker/*",dispatcherTypes = {DispatcherType.REQUEST})
public class UnsafeFilterWorker extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        var user = (UserReadDto) request.getSession()
                .getAttribute("user");
        if (user != null && user.getRole().name().equals("USER")) {
            chain.doFilter(request, response);
        } else {
            response.sendRedirect("/login");
        }
    }
}
