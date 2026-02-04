import java.sql.*;

public class SQLInjectionExample {

    public static void main(String[] args) {
        try {
            String userInput = "'; DROP TABLE users; --"; // User input containing malicious SQL injection

            // Create a connection to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");

            // Create a statement
            Statement statement = connection.createStatement();

            // Construct and execute the SQL query using user input directly
            String query = "SELECT * FROM users WHERE username = '" + userInput + "'";
            ResultSet resultSet = statement.executeQuery(query);

            // Process the query results
            while (resultSet.next()) {
                System.out.println(resultSet.getString("username"));
                System.out.println(resultSet.getString("email"));
            }

            // Close resources
            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
