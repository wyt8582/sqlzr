import java.sql.*;

public class VulnerableCode {
    public static void main(String[] args) {
        String userInput = "'; DROP TABLE users; --";

        try {
            // Establish connection to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");

            // Create a SQL statement with user input concatenated directly
            String sql = "SELECT * FROM users WHERE username = '" + userInput + "'";
            Statement statement = connection.createStatement();

            // Execute the SQL query
            ResultSet resultSet = statement.executeQuery(sql);

            // Process the result set
            while (resultSet.next()) {
                System.out.println(resultSet.getString("username"));
            }

            // Close connections
            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
           e.printStackTrace();
        }
    }
}
