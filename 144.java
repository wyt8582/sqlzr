import java.sql.*;

public class SQLInjectionVulnerable {

    public static void main(String[] args) {
        try {
            String userInput = "'; DROP TABLE users; --"; // User input containing malicious SQL injection code
            
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");
            Statement statement = connection.createStatement();
            
            // Vulnerable code - user input is directly concatenated into the query
            String query = "SELECT * FROM users WHERE username = '" + userInput + "'";
            
            ResultSet resultSet = statement.executeQuery(query);
            
            while (resultSet.next()) {
                System.out.println("Username: " + resultSet.getString("username"));
                System.out.println("Email: " + resultSet.getString("email"));
            }
            
            resultSet.close();
            statement.close();
            connection.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
