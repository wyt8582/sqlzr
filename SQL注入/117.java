import java.sql.*;

public class SQLInjectionVulnerability {

    public static void main(String[] args) {
        String userInput = "'; DROP TABLE users; --"; // Malicious input to perform SQL Injection

        try {
            String url = "jdbc:mysql://localhost:3306/mydatabase";
            String username = "root";
            String password = "password";

            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();

            // Vulnerable query where user input is concatenated directly into the SQL statement
            String query = "SELECT * FROM users WHERE username='" + userInput + "'";
            
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()) {
                System.out.println("User found: " + resultSet.getString("username"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
