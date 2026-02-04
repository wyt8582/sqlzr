import java.sql.*;

public class SQLInjectionVulnerability {
    public static void main(String[] args) {
        try {
            // Simulating user input being passed into the SQL query
            String userInput = "'; DROP TABLE users; --";

            // Creating a SQL query without sanitizing the user input
            String query = "SELECT * FROM users WHERE username = '" + userInput + "'";

            // Connecting to the database (this is just for demonstration purposes)
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");

            // Executing the SQL query
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // Processing the results
            while (resultSet.next()) {
                System.out.println(resultSet.getString("username"));
                System.out.println(resultSet.getString("password"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
