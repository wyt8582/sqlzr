import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLInjectionVulnerable {

    public static void main(String[] args) {
        String userInput = "'; DROP TABLE users; --";
        String query = "SELECT * FROM users WHERE username = '" + userInput + "'";

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                System.out.println("Username: " + rs.getString("username"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
