import java.sql.*;

public class SQLInjectionVulnerability {

    public static void main(String[] args) {
        try {
            String userInput = "'; DROP TABLE users; --"; // Malicious input

            // Connect to the database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");

            // Create a SQL statement
            Statement stmt = conn.createStatement();

            // Construct the SQL query using user input directly (vulnerable to SQL Injection)
            String sqlQuery = "SELECT * FROM users WHERE username='" + userInput + "'";

            // Execute the query
            ResultSet rs = stmt.executeQuery(sqlQuery);

            // Process the results
            while (rs.next()) {
                System.out.println("User ID: " + rs.getInt("id") + ", Username: " + rs.getString("username"));
            }

            // Close resources
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
