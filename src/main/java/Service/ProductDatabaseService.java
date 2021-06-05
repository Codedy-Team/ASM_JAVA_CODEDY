package Service;

import Model.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ProductDatabaseService {

    private DataSource dataSource;

    public ProductDatabaseService(DataSource dataSource) {
        this.dataSource = dataSource;
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
                connection.close();   // doesn't really close it ... just puts back in connection pool
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public void update(Product item) throws Exception {

        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            // get db connection
            myConn = dataSource.getConnection();

            // create SQL update statement
            String sql = "UPDATE product "
                    + "SET product_category_id = ?, restaurant_id = ?, name = ?, ingredients = ?, price = ?, image = ?, country = ?, tag = ?, description = ?, featured = ?, updated_at = ?, updated_by = ?, version = ?, deleted =?"
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
            myStmt.setDate(11, new Date(Calendar.getInstance().getTime().getTime()));
            myStmt.setString(12, "CODEDY");
            myStmt.setInt(13, item.getVersion() + 1);
            myStmt.setBoolean(14, item.getDeleted());

            // execute SQL statement
            myStmt.execute();
        }
        finally {
            // clean up JDBC objects
            close(myConn, myStmt, null);
        }
    }
}
