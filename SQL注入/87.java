import java.sql.*;

public class SQLInjectionVulnerability {
    public static void main(String[] args) {
        String userInput = "'; DROP TABLE users; --"; // Simulated malicious input
        
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");
            Statement stmt = conn.createStatement();
            
            // Vulnerable SQL query where user input is directly concatenated into the query string
            String query = "SELECT * FROM users WHERE username = '" + userInput + "'";
            
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                System.out.println("Username: " + rs.getString("username"));
                System.out.println("Email: " + rs.getString("email"));
                // Do something with the retrieved data
            }
            
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
