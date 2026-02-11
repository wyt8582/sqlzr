import java.sql.*;

public class SQLInjectionVulnerableCode {

    public static void main(String[] args) {
        try {
            String userInput = "'; DROP TABLE users; --"; // User input containing malicious SQL code
            
            String url = "jdbc:mysql://localhost:3306/mydatabase";
            String username = "root";
            String password = "password";
            
            Connection conn = DriverManager.getConnection(url, username, password);
            
            Statement statement = conn.createStatement();
            
            // Vulnerable code - Concatenating user input directly into the SQL query
            String query = "SELECT * FROM users WHERE username = '" + userInput + "'";
            
            ResultSet resultSet = statement.executeQuery(query);
            
            while(resultSet.next()) {
                System.out.println("Username: " + resultSet.getString("username"));
                System.out.println("Password: " + resultSet.getString("password"));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
