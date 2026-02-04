import java.sql.*;

public class SQLInjectionVulnerable {
    public static void main(String[] args) {
        try {
            // Simulating user input (this should be obtained from user input in a real scenario)
            String userInput = "admin'; DROP TABLE users; --";

            // Connecting to a database (this connection should be properly established in a real scenario)
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");

            // Vulnerable query construction
            String query = "SELECT * FROM users WHERE username = '" + userInput + "'";

            // Executing the query
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Displaying the results
            while (rs.next()) {
                System.out.println("Username: " + rs.getString("username"));
                System.out.println("Password: " + rs.getString("password"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
