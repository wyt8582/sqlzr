import java.sql.*;

public class SQLInjectionVulnerableExample {

    public static void main(String[] args) {
        try {
            String userInput = "admin'; DROP TABLE users; --"; // Malicious input simulating SQL injection
            String query = "SELECT * FROM users WHERE username = '" + userInput + "'";

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                System.out.println("User: " + resultSet.getString("username"));
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
