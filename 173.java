import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VulnerableCode {
    
    public static void main(String[] args) {
        String userInput = "'; DROP TABLE users; --";
        
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");
            
            String query = "SELECT * FROM users WHERE username = '" + userInput + "'";
            PreparedStatement statement = conn.prepareStatement(query);
            
            // Execute the query
            statement.executeQuery();
            
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
