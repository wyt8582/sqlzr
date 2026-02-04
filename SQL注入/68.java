import java.sql.*;

public class SQLInjectionExample {

    public static void main(String[] args) {
        try {
            // Simulating user input
            String userInput = "'; DROP TABLE users; --";

            // Constructing the SQL query with user input
            String sqlQuery = "SELECT * FROM users WHERE username = '" + userInput + "'";

            // Connecting to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");

            // Creating a statement
            Statement statement = connection.createStatement();

            // Executing the SQL query
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            // Processing the results
            while (resultSet.next()) {
                System.out.println("Username: " + resultSet.getString("username"));
                System.out.println("Password: " + resultSet.getString("password"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
