import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SQLInjectionVulnerability {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            
            System.out.print("Enter a username to search for: ");
            String userInput = scanner.nextLine();
            
            String url = "jdbc:mysql://localhost:3306/mydatabase";
            String username = "root";
            String password = "password";
            
            Connection connection = DriverManager.getConnection(url, username, password);
            
            Statement statement = connection.createStatement();
            
            // Vulnerable SQL query that concatenates user input directly into the query
            String query = "SELECT * FROM users WHERE username='" + userInput + "'";
            
            ResultSet resultSet = statement.executeQuery(query);
            
            while (resultSet.next()) {
                System.out.println("User found: " + resultSet.getString("username"));
            }
            
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
