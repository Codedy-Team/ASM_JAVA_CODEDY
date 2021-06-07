package Controller.dashboard;

import Model.Product;
import Service.ProductDatabaseService;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(name = "Create", value = "/admin/product/create")
public class Create extends HttpServlet {

    private ProductDatabaseService productDatabaseService;
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();

        Context initContext = null;
        try {
            initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/asm_codedy");
        } catch (NamingException e) {
            e.printStackTrace();
        }

        try {
            productDatabaseService = new ProductDatabaseService(dataSource);
        } catch (Exception exc) {
            throw new ServletException(exc);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/view/dashboard/create.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int productCategoryId = Integer.parseInt(request.getParameter("productCategoryId"));
        int restaurantId = Integer.parseInt(request.getParameter("restaurantId"));
        String name = request.getParameter("name");
        String ingredients = request.getParameter("ingredients");
        Double price = Double.valueOf(request.getParameter("price"));
        String image = request.getParameter("image");
        String country = request.getParameter("country");
        String tag = request.getParameter("tag");
        String description = request.getParameter("description");
        Boolean featured = Boolean.valueOf(request.getParameter("featured"));

        Product theProduct = new Product(productCategoryId, restaurantId, name, ingredients, price, image, country, tag, description, featured);

        try {
            productDatabaseService.Create(theProduct);
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/admin/product/create");

    }


}