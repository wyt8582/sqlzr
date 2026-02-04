import java.sql.*;

public class SQLInjectionVulnerable {

    public static void main(String[] args) {
        String userInput = "'; DROP TABLE users; --"; // User input containing SQL Injection payload

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");
            Statement statement = connection.createStatement();
            
            // Vulnerable query construction
            String query = "SELECT * FROM users WHERE username = '" + userInput + "'";
            
            ResultSet resultSet = statement.executeQuery(query);
            
            while(resultSet.next()) {
                System.out.println("Username: " + resultSet.getString("username"));
            }
            
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
