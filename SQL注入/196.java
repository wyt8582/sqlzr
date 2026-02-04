import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class SQLInjectionExample {
    public static void main(String[] args) {
        try {
            // Establish connection to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "root", "password");

            // Get user input for username
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter username: ");
            String userInput = scanner.nextLine();

            // Create SQL query with user input (vulnerable to SQL Injection)
            String query = "SELECT * FROM users WHERE username='" + userInput + "'";

            // Execute the query
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // Process the results
            while (resultSet.next()) {
                System.out.println("User ID: " + resultSet.getInt("id") + ", Username: " + resultSet.getString("username"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
