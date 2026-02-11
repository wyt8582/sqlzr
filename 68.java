import java.sql.*;

public class SQLInjectionExample {

    public static void main(String[] args) {
        String userInput = "'; DROP TABLE users; --"; // Malicious input

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");
            Statement statement = connection.createStatement();

            // Vulnerable query construction
            String query = "SELECT * FROM users WHERE username = '" + userInput + "'";
            
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                System.out.println("User found: " + resultSet.getString("username"));
            }

        } catch (SQLException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}
