import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLInjectionVulnerableCode {

    public static void main(String[] args) {
        String userInput = "'; DROP TABLE users; --";

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");
            Statement statement = connection.createStatement();

            // Vulnerable code: concatenating user input directly into the SQL query
            String query = "SELECT * FROM users WHERE username = '" + userInput + "'";
            
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                System.out.println("Username: " + resultSet.getString("username"));
                System.out.println("Email: " + resultSet.getString("email"));
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
