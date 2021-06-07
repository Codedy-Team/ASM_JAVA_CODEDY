package Controller.Authentication;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "Logout", urlPatterns = {"/logout", "/logout/"})
public class Logout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("isLoggedIn");
        request.getSession().removeAttribute("user");

        request.getSession().setAttribute("notification", "You have logged out successfully from the system.");

        response.sendRedirect(request.getContextPath() + "/login");
    }
}
