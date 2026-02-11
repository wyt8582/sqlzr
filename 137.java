import java.sql.*;

public class SQLInjectionVulnerableCode {
    public static void main(String[] args) {
        try {
            String userInput = "admin'; DROP TABLE users; --"; // User input with malicious intent

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");
            Statement statement = connection.createStatement();

            // Vulnerable SQL query construction
            String query = "SELECT * FROM users WHERE username='" + userInput + "'";

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
