import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SQLInjectionExample {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a username to search for:");
            String userInput = scanner.nextLine();

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");
            Statement statement = connection.createStatement();

            String query = "SELECT * FROM users WHERE username = '" + userInput + "'";
            
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                System.out.println("User found: " + resultSet.getString("username"));
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
