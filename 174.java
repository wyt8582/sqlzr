import java.sql.*;

public class VulnerableCode {

    public void login(String username, String password) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "username", "password");
            Statement statement = connection.createStatement();
            
            String query = "SELECT * FROM users WHERE username='" + username + "' AND password='" + password + "'";
            
            ResultSet resultSet = statement.executeQuery(query);
            
            if (resultSet.next()) {
                System.out.println("Login successful");
            } else {
                System.out.println("Login failed");
            }
            
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        VulnerableCode code = new VulnerableCode();
        
        // Simulate user input
        String userInputUsername = "admin' OR '1'='1";
        String userInputPassword = "password123";
        
        code.login(userInputUsername, userInputPassword);
    }
}
