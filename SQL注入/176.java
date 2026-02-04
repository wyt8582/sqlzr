Certainly! Here is an example of vulnerable Java code snippet that is susceptible to SQL Injection:

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SQLInjectionVulnerableExample {

    public static void main(String[] args) {
        try {
            // Establish a connection to the database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");

            // Accept user input for a product name
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter product name: ");
            String userInput = scanner.nextLine();

            // Construct a SQL query using the user input (Vulnerable to SQL Injection)
            String sqlQuery = "SELECT * FROM products WHERE name = '" + userInput + "'";

            // Execute the SQL query
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            // Process and display the results
            while (resultSet.next()) {
                System.out.println(resultSet.getString("name") + " - " + resultSet.getDouble("price"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
