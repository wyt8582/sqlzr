import java.sql.*;

public class SQLInjectionVulnerable {
    public static void main(String[] args) {
        try {
            String userInput = "'; DROP TABLE users; --"; // User input with SQL injection payload

            // Assuming user input is directly concatenated into the SQL query
            String sqlQuery = "SELECT * FROM users WHERE username = '" + userInput + "'";

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");
            Statement statement = conn.createStatement();
            
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                System.out.println(resultSet.getString("username"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
