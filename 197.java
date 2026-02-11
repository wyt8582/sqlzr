import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLInjectionVulnerable {

    public static void main(String[] args) {
        String userInput = "admin'; DROP TABLE users; --";

        try {
            // Establishing a connection to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");

            // Creating a statement
            Statement statement = connection.createStatement();

            // Constructing the SQL query with user input
            String query = "SELECT * FROM users WHERE username = '" + userInput + "'";

            // Executing the query
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                System.out.println("User found: " + resultSet.getString("username"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
