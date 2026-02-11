import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VulnerableCodeSnippet {

    public void getUserData(String username) {
        String query = "SELECT * FROM Users WHERE username = '" + username + "'";
        
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");
            PreparedStatement statement = connection.prepareStatement(query);
            
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                System.out.println("User ID: " + resultSet.getInt("id") + ", Username: " + resultSet.getString("username"));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        VulnerableCodeSnippet vulnerableCodeSnippet = new VulnerableCodeSnippet();
        
        // This input comes from user
        String userInput = "admin'; DROP TABLE Users; --";
        
        vulnerableCodeSnippet.getUserData(userInput);
    }
}
