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
import java.util.List;

@WebServlet(urlPatterns = {"/admin/product", "/admin/product/", "/admin/product/index", "/admin/product/index/"})
public class Index extends HttpServlet {
    private ProductDatabaseService productDbUtil;
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
            productDbUtil = new ProductDatabaseService(dataSource);
        } catch (Exception exc) {
            throw new ServletException(exc);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("search");

        List<Product> products = null;
        if (keyword != null) {
            products = Product.search(keyword);
        } else {
            try {
                products = productDbUtil.getProducts();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        request.setAttribute("PRODUCT_LIST", products);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/dashboard/index.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
