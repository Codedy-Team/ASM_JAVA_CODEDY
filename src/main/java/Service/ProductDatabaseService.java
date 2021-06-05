package Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ProductDatabaseService {

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
