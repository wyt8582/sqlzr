import java.sql.*;

public class SQLInjectionVulnerable {

    public static void main(String[] args) {
        try {
            String userInput = "'; DROP TABLE users; --";
            
            // Vulnerable code - user input is directly concatenated into the SQL query
            String sqlQuery = "SELECT * FROM users WHERE username = '" + userInput + "'";
            
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            
            while(resultSet.next()) {
                System.out.println("User found: " + resultSet.getString("username"));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
