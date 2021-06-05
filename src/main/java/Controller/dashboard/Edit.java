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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@WebServlet(name = "edit", value = "admin/product/edit")
public class Edit extends HttpServlet {
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

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        int product_category_id = Integer.parseInt(request.getParameter("product_category_id"));
        int restaurant_id = Integer.parseInt(request.getParameter("restaurant_id"));

        String name = request.getParameter("name");
        String ingredients = request.getParameter("ingredients");
        Double price = Double.parseDouble(request.getParameter("price"));
        String image = request.getParameter("image");
        String country = request.getParameter("country");
        String tag = request.getParameter("tag");
        String description = request.getParameter("description");
        Boolean featured = Boolean.parseBoolean(request.getParameter("featured"));
        String created_by = request.getParameter("created_by");
        java.util.Date created_at = null;
        try {
            created_at = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("created_at"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String updated_by = request.getParameter("updated_by");
        Date updated_at = new Date(Calendar.getInstance().getTime().getTime());
        int version =  Integer.parseInt(request.getParameter("version"));

        Product product = new Product(id, product_category_id, restaurant_id, name, ingredients,price,image, country, tag,description, featured,
                created_at,created_by,updated_at, updated_by,version,false );


        try {
            productDatabaseService.update(product);
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/product-menu/show/?id=" + id);

    }
}
