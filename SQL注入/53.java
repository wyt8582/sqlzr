import java.sql.*;

public class SQLInjectionVulnerableExample {

    public static void main(String[] args) {
        String userInput = "admin'; DROP TABLE users; --"; // Malicious input for SQL Injection
        String query = "SELECT * FROM users WHERE username = '" + userInput + "'";

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "root", "password");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                System.out.println("Username: " + resultSet.getString("username"));
                System.out.println("Password: " + resultSet.getString("password"));
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
