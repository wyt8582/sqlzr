import java.sql.*;

public class VulnerableCode {

    public static void main(String[] args) {
        String userInput = "'; DROP TABLE users; --"; // User input with malicious SQL injection payload
        
        try {
            String url = "jdbc:mysql://localhost:3306/mydatabase";
            String username = "root";
            String password = "password";
            
            Connection connection = DriverManager.getConnection(url, username, password);
            
            Statement statement = connection.createStatement();
            
            // Vulnerable code where user input is directly concatenated into the SQL query
            String query = "SELECT * FROM users WHERE username = '" + userInput + "'";
            
            ResultSet resultSet = statement.executeQuery(query);
            
            while(resultSet.next()) {
                System.out.println(resultSet.getString("username"));
                System.out.println(resultSet.getString("email"));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
