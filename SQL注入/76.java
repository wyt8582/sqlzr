import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class SQLInjectionVulnerability {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter username: ");
            String username = scanner.nextLine();

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");
            Statement statement = connection.createStatement();

            // Vulnerable code - user input directly concatenated into SQL query
            String query = "SELECT * FROM users WHERE username='" + username + "'";
            
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()) {
                System.out.println("User found: " + resultSet.getString("username"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
