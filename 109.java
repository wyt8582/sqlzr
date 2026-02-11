import java.sql.*;

public class SQLInjectionVulnerable {
    public static void main(String[] args) {
        try {
            // Get user input (simulated)
            String userInput = "'; DROP TABLE users; --";

            // Construct SQL query with user input
            String query = "SELECT * FROM users WHERE username = '" + userInput + "'";

            // Establish database connection
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");

            // Create a statement object
            Statement statement = connection.createStatement();

            // Execute the query
            ResultSet resultSet = statement.executeQuery(query);

            // Process the result set (not shown for brevity)
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
