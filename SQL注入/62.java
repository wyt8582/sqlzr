import java.sql.*;

public class SQLInjectionVulnerability {
    public static void main(String[] args) {
        String userInput = "'; DROP TABLE users; --"; // Malicious input simulating SQL Injection
        
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");
            
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM users WHERE username = '" + userInput + "'";
            
            ResultSet resultSet = statement.executeQuery(query);
            
            while(resultSet.next()) {
                System.out.println("Username: " + resultSet.getString("username"));
                System.out.println("Email: " + resultSet.getString("email"));
            }
            
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
