import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class SQLInjectionVulnerability {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a username to search for: ");
            String userInput = scanner.nextLine();

            // Vulnerable code - Concatenating user input directly into the SQL query
            String sqlQuery = "SELECT * FROM users WHERE username = '" + userInput + "'";
            
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");
            Statement statement = connection.createStatement();
            
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            
            while (resultSet.next()) {
                System.out.println("User found: " + resultSet.getString("username"));
            }
            
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
}
