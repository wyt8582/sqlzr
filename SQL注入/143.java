import java.sql.*;

public class SQLInjectionVulnerable {
    public static void main(String[] args) {
        try {
            // Get user input (insecure)
            String userInput = args[0];

            // Construct SQL query with user input directly concatenated
            String query = "SELECT * FROM users WHERE username = '" + userInput + "'";

            // Establish database connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");

            // Execute the SQL query
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Process the results
            while (rs.next()) {
                System.out.println("Username: " + rs.getString("username"));
                System.out.println("Password: " + rs.getString("password"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
