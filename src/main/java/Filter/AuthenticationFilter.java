package Filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(urlPatterns = {"/*"})
public class AuthenticationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        Boolean isLoggedIn = (Boolean) (httpRequest.getSession().getAttribute("isLoggedIn"));
        if (isLoggedIn == null) {
            isLoggedIn = false;
        }

        String action = httpRequest.getServletPath();
        if ("/admin".equals(action)) { //chuyển hướng
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/admin/product");
            return;
        }
        if ("/".equals(action) || "/login".equals(action) || "/login.jsp".equals(action)) {
            if (isLoggedIn) {
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/");
                return;
            }

            chain.doFilter(request, response);
            return;
        } else {
            if (isLoggedIn) {
                chain.doFilter(request, response);
                return;
            }

            String uri = httpRequest.getRequestURI();
            if (uri.contains("admin")) {
                httpRequest.getSession().setAttribute("errorMessage", "Please login to access this action");
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/login?urlContinue=" + action.substring(1));
                return;
            }
        }

        chain.doFilter(request, response);
    }
}
