import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SQLInjectionVulnerableCode {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a username: ");
            String username = scanner.nextLine();

            // Vulnerable code: user input directly concatenated into the SQL query
            String query = "SELECT * FROM users WHERE username='" + username + "'";
            
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");
            Statement statement = connection.createStatement();
            
            ResultSet resultSet = statement.executeQuery(query);
            
            while(resultSet.next()) {
                System.out.println("User ID: " + resultSet.getInt("id") + ", Username: " + resultSet.getString("username"));
            }
            
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
