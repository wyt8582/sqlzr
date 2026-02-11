import java.sql.*;

public class VulnerableCode {
    public static void main(String[] args) {
        String userInput = args[0]; // Simulating user input
        String query = "SELECT * FROM users WHERE username = '" + userInput + "'";

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()) {
                System.out.println("User found: " + resultSet.getString("username"));
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
