package Service;

import Model.Category;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDatabaseService {
    private DataSource dataSource;

    public CategoryDatabaseService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Category> all() throws Exception {

        List<Category> items = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // get a connection
            connection = dataSource.getConnection();

            // create sql statement
            String sql = "select * from category WHERE deleted = false order by id desc ";

            statement = connection.createStatement();

            // execute query
            resultSet = statement.executeQuery(sql);

            // process result set
            while (resultSet.next()) {
                // get new item object
                Category tempItem = getCategoryFormResultSet(resultSet);

                // add it to the list of items
                items.add(tempItem);
            }

            return items;
        } finally {
            // close JDBC objects
            close(connection, statement, resultSet);
        }
    }

    public Category find(int id) throws Exception {
        Category item = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // get connection to database
            connection = dataSource.getConnection();

            // create sql to get selected item
            String sql = "select * from category where deleted = false and id = ?";

            // create prepared statement
            preparedStatement = connection.prepareStatement(sql);

            // set params
            preparedStatement.setInt(1, id);

            // execute statement
            resultSet = preparedStatement.executeQuery();

            // retrieve data from result set row
            if (resultSet.next()) {
                item = getCategoryFormResultSet(resultSet);
            } else {
                throw new Exception("Could not find item id: " + id);
            }

            return item;
        } finally {
            // clean up JDBC objects
            close(connection, preparedStatement, resultSet);
        }
    }


    private Category getCategoryFormResultSet(ResultSet resultSet) throws SQLException {
        // create new item object
        Category item = new Category();

        // retrieve data from result set row & set data
        item.setId(resultSet.getInt("id"));

        item.setName(resultSet.getString("name"));
        item.setImage(resultSet.getString("image"));
        item.setActive(resultSet.getInt("active"));

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
                connection.close();   // doesn't really close it ... just puts back in connection pool
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
