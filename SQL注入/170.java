import java.sql.*;

public class SQLInjectionVulnerable {
    public static void main(String[] args) {
        try {
            // Simulating user input
            String userInput = "'; DROP TABLE users; --";

            // Creating a connection to a SQLite database
            Connection connection = DriverManager.getConnection("jdbc:sqlite:test.db");

            // Creating a SQL statement with user input concatenated directly
            String sql = "SELECT * FROM users WHERE username = '" + userInput + "'";

            // Executing the SQL query
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            // Processing the query result
            while (resultSet.next()) {
                System.out.println("Username: " + resultSet.getString("username"));
                System.out.println("Email: " + resultSet.getString("email"));
            }

            // Closing resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
