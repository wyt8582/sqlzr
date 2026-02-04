import java.sql.*;

public class SQLInjectionVulnerable {

    public static void main(String[] args) {
        try {
            // Simulating user input
            String userInput = "'; DROP TABLE users; --";

            // Constructing the SQL query with user input directly concatenated
            String query = "SELECT * FROM users WHERE username = '" + userInput + "'";

            // Establishing a connection to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");

            // Executing the query
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
