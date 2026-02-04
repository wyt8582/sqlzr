Certainly! Below is a Java code snippet that demonstrates a vulnerability related to SQL Injection:

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class SQLInjectionExample {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter username: ");
            String username = scanner.nextLine();

            String url = "jdbc:mysql://localhost:3306/mydatabase";
            String user = "root";
            String password = "password";
            
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            
            // Vulnerability: User input is directly concatenated into the SQL query
            String query = "SELECT * FROM users WHERE username='" + username + "'";
            
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                System.out.println("User found with ID: " + resultSet.getInt("id"));
                System.out.println("Name: " + resultSet.getString("name"));
                System.out.println("Email: " + resultSet.getString("email"));
                System.out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
