import java.sql.*;

public class VulnerableSQLInjection {
    public static void main(String[] args) {
        try {
            String userInput = args[0];
            
            String url = "jdbc:mysql://localhost:3306/mydatabase";
            String username = "myusername";
            String password = "mypassword";
            
            Connection connection = DriverManager.getConnection(url, username, password);
            
            Statement statement = connection.createStatement();
            
            String query = "SELECT * FROM users WHERE username='" + userInput + "'";
            
            ResultSet resultSet = statement.executeQuery(query);
            
            while(resultSet.next()) {
                System.out.println("User ID: " + resultSet.getInt("id") + ", Username: " + resultSet.getString("username"));
            }
            
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
