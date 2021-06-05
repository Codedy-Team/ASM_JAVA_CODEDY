package Service;

import Model.Product;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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

    private Product getProductFormResultSet(ResultSet resultSet) throws SQLException, SQLException {

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
}
