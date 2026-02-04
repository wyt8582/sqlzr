Sure, here is a Java code snippet that demonstrates a vulnerability related to SQL Injection:

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SQLInjectionExample {
    public static void main(String[] args) {
        try {
            // Establishing a connection to a database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");
            
            // Taking user input for username
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            
            // Creating and executing an SQL query using user input without proper validation
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM users WHERE username='" + username + "'";
            ResultSet rs = stmt.executeQuery(query);
            
            // Processing the result set
            while (rs.next()) {
                System.out.println("User ID: " + rs.getInt("id") + ", Username: " + rs.getString("username"));
            }
            
            // Closing resources
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e
