import java.sql.*;

public class SQLInjectionVulnerable {

    public static void main(String[] args) {
        try {
            // Simulating user input being used in a SQL query
            String userInput = "'; DROP TABLE users; --";

            // Creating a SQL query by concatenating user input
            String query = "SELECT * FROM users WHERE username = '" + userInput + "'";

            // Connecting to the database and executing the query
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");
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
