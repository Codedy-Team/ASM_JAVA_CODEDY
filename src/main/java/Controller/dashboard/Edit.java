package Controller.dashboard;

import Model.Product;
import Service.ProductDatabaseService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@WebServlet(urlPatterns = {"/admin/product/edit/"})
public class Edit extends HttpServlet {
    private ProductDatabaseService productDatabaseService;
    private DataSource dataSource;
    Product theProduct = null;

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
        // read product id from form data
        String theProductId = request.getParameter("id");
        // get product from database (db util)
        try {
            theProduct = productDatabaseService.getProduct(theProductId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // place student in the request attribute
        request.setAttribute("product", theProduct);
        request.getRequestDispatcher("/view/dashboard/edit.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idStr =  request.getParameter("id");
        try {
            Product product = productDatabaseService.getProduct(idStr);
            product.setProductCategoryId(Integer.parseInt(request.getParameter("productCategoryId")));
            product.setRestaurantId(Integer.parseInt(request.getParameter("restaurantId")));
            product.setName(request.getParameter("name"));
            product.setIngredients(request.getParameter("ingredients"));
            product.setPrice(Double.parseDouble(request.getParameter("price")));
            product.setImage(request.getParameter("image"));
            product.setCountry(request.getParameter("country"));
            product.setTag(request.getParameter("tag"));
            product.setDescription(request.getParameter("description"));
            product.setFeatured(Boolean.parseBoolean(request.getParameter("featured")));

            product.setVersion(Integer.parseInt(request.getParameter("version")));
            productDatabaseService.update(product);
       response.sendRedirect(request.getContextPath() + "/product/show/?id=" + product.getId());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void loadProduct(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // read student id from form data
        String theStudentId = request.getParameter("id");

        // get student from database (db util)
        Product theProduct = productDatabaseService.getProduct(theStudentId);

        // place student in the request attribute
        request.setAttribute("THE_PRODUCT", theProduct);

        // send to jsp page: update-student-form.jsp
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/edit.jsp");
        dispatcher.forward(request, response);
    }
}
