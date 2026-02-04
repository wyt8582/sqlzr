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
            System.out.print("Enter a username: ");
            String username = scanner.nextLine();

            String url = "jdbc:mysql://localhost:3306/mydatabase";
            String user = "root";
            String password = "password";

            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();

            // Vulnerable SQL query where user input is directly concatenated into the query
            String query = "SELECT * FROM users WHERE username='" + username + "'";
            
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()) {
                System.out.println("User ID: " + resultSet.getInt("id") + ", Username: " + resultSet.getString("username"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
