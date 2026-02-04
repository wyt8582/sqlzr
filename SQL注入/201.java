import java.sql.*;

public class SQLInjectionVulnerable {
    public static void main(String[] args) {
        try {
            String userInput = "'; DROP TABLE users; --"; // Simulating malicious user input

            // Vulnerable code that concatenates user input directly into the SQL query
            String sqlQuery = "SELECT * FROM users WHERE username = '" + userInput + "'";

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);

            while (rs.next()) {
                System.out.println("User found: " + rs.getString("username"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
