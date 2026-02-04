import java.sql.*;

public class VulnerableCode {
    public static void main(String[] args) {
        String userInput = args[0]; // User input from command line argument

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");
            Statement statement = connection.createStatement();

            // Vulnerable query construction
            String query = "SELECT * FROM users WHERE username='" + userInput + "'";
            
            ResultSet resultSet = statement.executeQuery(query);
            
            while(resultSet.next()) {
                System.out.println("User ID: " + resultSet.getInt("id") + ", Username: " + resultSet.getString("username"));
            }
            
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
