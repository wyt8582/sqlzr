import java.sql.*;

public class SQLInjectionExample {

    public static void main(String[] args) {
        String userInput = "'; DROP TABLE users; --"; // User input with malicious SQL Injection payload

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");
            Statement stmt = conn.createStatement();

            // Vulnerable query construction where user input is directly concatenated
            String query = "SELECT * FROM users WHERE username = '" + userInput + "'";
            
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                System.out.println("Username: " + rs.getString("username"));
                System.out.println("Email: " + rs.getString("email"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
