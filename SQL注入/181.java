import java.sql.*;

public class SQLInjectionVulnerable {
    public static void main(String[] args) {
        try {
            String userInput = "'; DROP TABLE users; --"; // User input with malicious SQL injection payload

            // Connect to the database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");

            // Create a SQL statement with user input concatenated directly into the query
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM users WHERE username='" + userInput + "'";
            ResultSet rs = stmt.executeQuery(query);

            // Process the result set
            while (rs.next()) {
                System.out.println(rs.getString("username"));
                System.out.println(rs.getString("password"));
            }

            // Close connections
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
