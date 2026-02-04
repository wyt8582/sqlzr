import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class VulnerableCode {
    public static void main(String[] args) {
        try {
            // Get user input
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter username: ");
            String username = scanner.nextLine();

            // Vulnerable code: concatenating user input directly into the SQL query
            String query = "SELECT * FROM users WHERE username='" + username + "'";
            
            // Establish database connection
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");
            
            Statement statement = connection.createStatement();
            
            // Execute the SQL query
            ResultSet resultSet = statement.executeQuery(query);
            
            // Display the results
            while(resultSet.next()) {
                System.out.println("User found: " + resultSet.getString("username"));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
