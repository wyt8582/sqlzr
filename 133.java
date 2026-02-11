import java.sql.*;

public class SQLInjectionVulnerableExample {
    public static void main(String[] args) {
        try {
            String userInput = "admin'; DROP TABLE users;--"; // Simulating user input containing SQL Injection
            String query = "SELECT * FROM users WHERE username = '" + userInput + "'";

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "root", "password");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                System.out.println("Username: " + resultSet.getString("username"));
                System.out.println("Email: " + resultSet.getString("email"));
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
