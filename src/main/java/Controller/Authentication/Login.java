package Controller.Authentication;

import Model.User;
import Service.UserServiceSession;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/login", "/login/"})
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/view/authentication/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String urlContinue = request.getParameter("urlContinue");

        User user = UserServiceSession.check(request, username, password);

        if (user != null) {
            request.getSession().setAttribute("isLoggedIn", true);
            request.getSession().setAttribute("user", user);

            response.sendRedirect(request.getContextPath() + "/" + urlContinue);
        } else {
            request.getSession().setAttribute("errorMessage", "Invalid Username/Password.");

            response.sendRedirect(request.getContextPath() + "/login/?urlContinue=" + urlContinue);
        }
    }
}
