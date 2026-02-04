import java.sql.*;

public class SQLInjectionExample {

    public static void main(String[] args) {
        try {
            String userInput = "'; DROP TABLE users; --"; // Malicious input causing SQL Injection

            String url = "jdbc:mysql://localhost:3306/mydatabase";
            String username = "root";
            String password = "password";

            Connection conn = DriverManager.getConnection(url, username, password);

            Statement stmt = conn.createStatement();

            // Vulnerable code where user input is concatenated directly into the SQL query
            String query = "SELECT * FROM users WHERE username='" + userInput + "'";
            
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                System.out.println("Username: " + rs.getString("username"));
                System.out.println("Password: " + rs.getString("password"));
                System.out.println("-------------------------");
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
