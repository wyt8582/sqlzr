Sure, here is a Java code snippet that demonstrates a vulnerability related to SQL Injection:

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class SQLInjectionVulnerability {
    public static void main(String[] args) {
        try {
            // Establish database connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");
            
            // Get user input
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            
            // Construct SQL query with user input (vulnerable to SQL injection)
            String query = "SELECT * FROM users WHERE username='" + username + "'";
            
            // Execute the query
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            // Process the results
            while (rs.next()) {
                System.out.println("User found: " + rs.getString("username"));
                // Add additional processing here as needed
            }
            
            // Close resources
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
           e.printStackTrace
