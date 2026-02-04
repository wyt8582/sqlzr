import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class SQLInjectionVulnerableCode {

    public static void main(String[] args) {
        try {
            // Establish database connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");
            
            // Get user input for username
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            
            // Vulnerable SQL query with user input concatenated directly
            String query = "SELECT * FROM users WHERE username='" + username + "'";
            
            Statement stmt = conn.createStatement();
            
            // Execute the vulnerable SQL query
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()){
                System.out.println("User ID: " + rs.getInt("id") + ", Username: " + rs.getString("username"));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
