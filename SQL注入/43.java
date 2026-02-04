import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class SQLInjectionVulnerability {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter a username to search for: ");
            String userInput = scanner.nextLine();

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");
            Statement stmt = conn.createStatement();
            
            // Vulnerable code - concatenating user input directly into the SQL query
            String query = "SELECT * FROM users WHERE username='" + userInput + "'";
            
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                System.out.println("User found: " + rs.getString("username"));
            }
            
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
