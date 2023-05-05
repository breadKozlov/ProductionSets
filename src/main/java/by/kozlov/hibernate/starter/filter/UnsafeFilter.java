package by.kozlov.hibernate.starter.filter;

import by.kozlov.hibernate.starter.dto.UserDto;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/admin")
public class UnsafeFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var user = (UserDto)((HttpServletRequest) servletRequest).getSession()
                .getAttribute("user");
        if (user != null && user.getRole().name().equals("ADMIN")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            ((HttpServletResponse) servletResponse).sendRedirect("./registration");
        }
    }
}
