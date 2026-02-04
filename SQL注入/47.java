import java.sql.*;

public class SQLInjectionVulnerableCode {

    public static void main(String[] args) {
        String userInput = "'; DROP TABLE users; --"; // Malicious input simulating a SQL Injection attack

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");
            Statement stmt = conn.createStatement();

            // Vulnerable code where user input is directly concatenated into the SQL query
            String query = "SELECT * FROM users WHERE username='" + userInput + "'";
            
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                System.out.println("User: " + rs.getString("username"));
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
