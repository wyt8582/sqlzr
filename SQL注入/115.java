import java.sql.*;

public class SQLInjectionVulnerable {

    public static void main(String[] args) {
        try {
            String userInput = "'; DROP TABLE users; --"; // Malicious input

            // Connecting to the database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");
            
            // Creating a statement
            Statement stmt = conn.createStatement();
            
            // Executing a query with user input directly concatenated into the SQL query
            String query = "SELECT * FROM users WHERE username = '" + userInput + "'";
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                System.out.println(rs.getString("username"));
            }
            
            // Closing resources
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
           e.printStackTrace();
        }
    }
}
