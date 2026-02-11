import java.sql.*;

public class SQLInjectionVulnerable {
    public static void main(String[] args) {
        String userInput = "'; DROP TABLE users; --"; // Malicious input

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");
            Statement statement = connection.createStatement();

            String query = "SELECT * FROM users WHERE username='" + userInput + "'";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                System.out.println(resultSet.getString("username"));
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
