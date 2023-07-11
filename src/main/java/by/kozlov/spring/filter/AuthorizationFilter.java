package by.kozlov.spring.filter;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Set;

import static by.kozlov.spring.utils.UrlPath.LOGIN;
import static by.kozlov.spring.utils.UrlPath.REGISTRATION;

@WebFilter(urlPatterns = "/*",dispatcherTypes = {DispatcherType.REQUEST})
public class AuthorizationFilter extends HttpFilter {

    private static final Set<String> PUBLIC_PATH = Set.of(LOGIN, REGISTRATION);
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        var uri = request.getRequestURI();
        if (isPublicPath(uri) || isUserLoggedIn(request)) {
            chain.doFilter(request,response);
        } else  {
            response.sendRedirect("/login");
        }
    }
    private boolean isUserLoggedIn(HttpServletRequest servletRequest) {
        var user = servletRequest.getSession().getAttribute("user");
        return user != null;
    }
    private boolean isPublicPath(String uri) {
        return PUBLIC_PATH.stream().anyMatch(uri::startsWith);
    }
}
