import java.sql.*;

public class SQLInjectionVulnerability {
    public static void main(String[] args) {
        try {
            String userInput = "admin'; DROP TABLE users; --"; // User input that simulates a SQL injection attack
            String query = "SELECT * FROM users WHERE username = '" + userInput + "'";
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                System.out.println("Username: " + rs.getString("username"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("---------------------");
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
