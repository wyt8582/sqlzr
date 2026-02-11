import java.sql.*;

public class SQLInjectionVulnerableExample {

    public static void main(String[] args) {
        String userInput = "'; DROP TABLE users; --"; // Malicious input to perform SQL Injection
        
        try {
            String url = "jdbc:mysql://localhost:3306/mydatabase";
            String username = "root";
            String password = "password";
            
            Connection conn = DriverManager.getConnection(url, username, password);
            
            Statement stmt = conn.createStatement();
            
            // Vulnerable code - user input directly concatenated into SQL query
            String query = "SELECT * FROM users WHERE username = '" + userInput + "'";
            
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                System.out.println("User found: " + rs.getString("username"));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
