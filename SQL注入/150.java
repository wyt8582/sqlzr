import java.sql.*;

public class SQLInjectionVulnerableExample {

    public static void main(String[] args) {
        String userInput = "'; DROP TABLE users; --"; // User input containing malicious SQL code
        
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");
            Statement stmt = conn.createStatement();
            
            // Vulnerable query construction
            String query = "SELECT * FROM users WHERE username='" + userInput + "'";
            
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                System.out.println("Username: " + rs.getString("username"));
                System.out.println("Password: " + rs.getString("password"));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
