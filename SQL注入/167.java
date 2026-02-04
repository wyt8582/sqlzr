import java.sql.*;

public class SQLInjectionVulnerable {
    public static void main(String[] args) {
        try {
            // Simulating user input
            String userInput = "'; DROP TABLE users; --";

            // Constructing SQL query with user input
            String query = "SELECT * FROM users WHERE username = '" + userInput + "'";

            // Connecting to the database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");

            // Executing the query
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                System.out.println("Username: " + rs.getString("username"));
                System.out.println("Password: " + rs.getString("password"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
