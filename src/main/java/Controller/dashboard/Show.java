package Controller.dashboard;

import Model.Product;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "Show", urlPatterns = {"/admin/product/show", "/admin/product/show/"})
public class Show extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Product product = Product.find(Integer.parseInt(request.getParameter("id")));

        if (product == null) {
            response.setContentType("text/html");
            response.getWriter().println("Hieu_iceTea said: [404] The record does not exist or deleted. 😱 😥 ☹ 🤨 😴");
            return;
        }

        request.setAttribute("product", product);

        request.getRequestDispatcher("/view/dashboard/show.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
