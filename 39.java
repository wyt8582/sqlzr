import java.sql.*;

public class SQLInjectionVulnerableApp {

    public static void main(String[] args) {
        try {
            String userInput = "'; DROP TABLE users; --";
            
            // Improperly handling user input by directly concatenating it into the SQL query
            String sqlQuery = "SELECT * FROM users WHERE username = '" + userInput + "'";
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");
            Statement stmt = conn.createStatement();
            
            ResultSet rs = stmt.executeQuery(sqlQuery);
            
            while (rs.next()) {
                System.out.println("User ID: " + rs.getInt("id") + ", Username: " + rs.getString("username"));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
