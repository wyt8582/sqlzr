Certainly! Below is a Java code snippet that demonstrates a vulnerability related to SQL Injection where user input is improperly handled:

```java
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
            System.out.println("Enter a username to search for:");
            String userInput = scanner.nextLine();
            
            // Construct SQL query with user input
            String query = "SELECT * FROM Users WHERE username = '" + userInput + "'";
            
            // Connect to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");
            
            // Execute the query
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            
            // Process the results
            while (resultSet.next()) {
                System.out.println("User found: " + resultSet.getString("username"));
            }
            
            // Close resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
           e.printStackTrace();
