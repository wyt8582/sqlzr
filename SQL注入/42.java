import java.sql.*;

public class SQLInjectionVulnerable {

    public static void main(String[] args) {
        try {
            // Simulating user input
            String userInput = "'; DROP TABLE users; --";

            // Connecting to a SQLite database (for demonstration purposes)
            Connection conn = DriverManager.getConnection("jdbc:sqlite:test.db");

            // Creating a Statement object
            Statement stmt = conn.createStatement();

            // Vulnerable SQL query where user input is not properly handled
            String sql = "SELECT * FROM users WHERE username = '" + userInput + "'";

            // Executing the query
            ResultSet rs = stmt.executeQuery(sql);

            // Processing the result set (not relevant for demonstrating the vulnerability)

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
