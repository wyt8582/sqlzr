import java.sql.*;

public class SQLInjectionVulnerability {
    public static void main(String[] args) {
        try {
            String userInput = "'; DROP TABLE users; --"; // Simulated user input with malicious intent
            
            // Connect to the database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");
            
            // Create a SQL statement
            Statement stmt = conn.createStatement();
            
            // Vulnerable query construction - concatenating user input directly into the SQL query
            String query = "SELECT * FROM users WHERE username='" + userInput + "'";
            
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                System.out.println(rs.getString("username"));
                System.out.println(rs.getString("email"));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
