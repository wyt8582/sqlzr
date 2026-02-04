import java.sql.*;

public class SQLInjectionVulnerable {

    public static void main(String[] args) {
        String userInput = "admin'; DROP TABLE users; --";

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");
            Statement statement = connection.createStatement();
            
            // Vulnerable code where user input is concatenated directly into the SQL query
            String query = "SELECT * FROM users WHERE username='" + userInput + "'";
            
            ResultSet resultSet = statement.executeQuery(query);
            
            while (resultSet.next()) {
                System.out.println(resultSet.getString("username"));
            }
            
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
