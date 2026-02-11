import java.sql.*;

public class SQLInjectionExample {

    public static void main(String[] args) {
        try {
            String userInput = "'; DROP TABLE users; --"; // Malicious input simulating SQL Injection

            String url = "jdbc:mysql://localhost:3306/mydatabase";
            String username = "root";
            String password = "password";

            Connection connection = DriverManager.getConnection(url, username, password);
            
            // Vulnerable SQL statement with concatenation of user input
            String query = "SELECT * FROM users WHERE username = '" + userInput + "'";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                System.out.println("User ID: " + resultSet.getInt("id") + ", Username: " + resultSet.getString("username"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
