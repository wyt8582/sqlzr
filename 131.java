import java.sql.*;

public class SQLInjectionVulnerability {
    public static void main(String[] args) {
        String userInput = "admin'; DROP TABLE users; --"; // User input containing SQL Injection payload

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");
            Statement stmt = conn.createStatement();

            // Vulnerable code - user input directly concatenated into the SQL query
            String query = "SELECT * FROM users WHERE username='" + userInput + "'";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                System.out.println("Username: " + rs.getString("username"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
