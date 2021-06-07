package Service;

import Model.Product;
import jakarta.servlet.ServletException;

import javax.sql.DataSource;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ProductDatabaseService {

    private DataSource dataSource;

    public ProductDatabaseService (DataSource theDataSource) {
        dataSource = theDataSource;
    }

    public List<Product> getProducts() throws Exception {

        List<Product> items = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();

            String sql = "select * from product WHERE deleted = false order by id desc ";

            statement = connection.createStatement();

            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Product tempItem = getProductFormResultSet(resultSet);

                items.add(tempItem);
            }

            return items;
        } finally {
            close(connection, statement, resultSet);
        }
    }

    public Product getProductFormResultSet(ResultSet resultSet) throws SQLException, SQLException {

        Product item = new Product();

        item.setId(resultSet.getInt("id"));

        item.setProductCategoryId(resultSet.getInt("product_category_id"));
        item.setRestaurantId(resultSet.getInt("restaurant_id"));
        item.setName(resultSet.getString("name"));
        item.setIngredients(resultSet.getString("ingredients"));
        item.setPrice(resultSet.getDouble("price"));
        item.setImage(resultSet.getString("image"));
        item.setCountry(resultSet.getString("country"));
        item.setTag(resultSet.getString("tag"));
        item.setDescription(resultSet.getString("description"));
        item.setFeatured(resultSet.getBoolean("featured"));

        item.setCreatedAt(resultSet.getDate("created_at"));
        item.setCreatedBy(resultSet.getString("created_by"));
        item.setUpdatedAt(resultSet.getDate("updated_at"));
        item.setUpdatedBy(resultSet.getString("updated_by"));
        item.setVersion(resultSet.getInt("version"));
        item.setDeleted(resultSet.getBoolean("deleted"));

        return item;
    }
    public void update(Product item) throws Exception  {

        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            // get db connection
            myConn = dataSource.getConnection();

            // create SQL update statement
            String sql = "UPDATE product "
                    + "SET product_category_id = ?, restaurant_id = ?, name = ?, ingredients = ?, price = ?, image = ?, country = ?, tag = ?, description = ?, featured = ?, updated_at = ?, updated_by = ?, version = ? "
                    + "WHERE id = ?";

            // prepare statement
            myStmt = myConn.prepareStatement(sql);

            // set params
            myStmt.setInt(1, item.getProductCategoryId());
            myStmt.setInt(2, item.getRestaurantId());
            myStmt.setString(3, item.getName());
            myStmt.setString(4, item.getIngredients());
            myStmt.setDouble(5, item.getPrice());
            myStmt.setString(6, item.getImage());
            myStmt.setString(7, item.getCountry());
            myStmt.setString(8, item.getTag());
            myStmt.setString(9, item.getDescription());
            myStmt.setBoolean(10, item.getFeatured());
            myStmt.setString(12, "MH");
            myStmt.setInt(13, item.getVersion() + 1);
            myStmt.setInt(14, item.getId());
            myStmt.setString(11, (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(Calendar.getInstance().getTime()));


            // execute SQL statement
            myStmt.execute();
        }
        finally {
            // clean up JDBC objects
            close(myConn, myStmt, null);
        }
    }
    public Product getProduct(String theProductId) throws Exception {

        Product theProduct = null;

        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        int productId = Integer.parseInt(theProductId);

        try {
            // convert student id to int
//            productId = Integer.parseInt(theProductId);

            // get connection to database
            myConn = dataSource.getConnection();

            // create sql to get selected student
            String sql = "select * from product where id=?";

            // create prepared statement
            myStmt = myConn.prepareStatement(sql);

            // set params
            myStmt.setInt(1, productId);

            // execute statement
            myRs = myStmt.executeQuery();

            // retrieve data from result set row

            if (myRs.next()) {
                 theProduct = getProductFormResultSet(myRs);
            }

            return theProduct;
        } finally {
            close(myConn, myStmt, myRs);
        }
    }


    private void close(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connection != null) {
                connection.close();
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public void Create(Product theProduct) throws Exception {
        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            myConn = dataSource.getConnection();

            String sql = "insert into product "
                    + "(productCategoryId, restaurantId, name, ingredients, price, image, country, tag, description, featured)"
                    + "value (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            myStmt = myConn.prepareStatement(sql);

            myStmt.setInt(1, theProduct.getProductCategoryId());
            myStmt.setInt(2, theProduct.getRestaurantId());
            myStmt.setString(3, theProduct.getName());
            myStmt.setString(4, theProduct.getIngredients());
            myStmt.setDouble(5, theProduct.getPrice());
            myStmt.setString(6, theProduct.getImage());
            myStmt.setString(7, theProduct.getCountry());
            myStmt.setString(8, theProduct.getTag());
            myStmt.setString(9, theProduct.getDescription());
            myStmt.setBoolean(10, theProduct.getFeatured());

            myStmt.execute();
        }
        finally {
            close(myConn, myStmt, null);
        }

    }

}