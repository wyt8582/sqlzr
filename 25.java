import java.sql.*;

public class SQLInjectionVulnerable {

    public static void main(String[] args) {
        try {
            String userInput = "admin'; DROP TABLE users; --";
            String query = "SELECT * FROM users WHERE username = '" + userInput + "'";

            // Establishing a connection to the database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "root", "password");

            // Creating a statement object
            Statement stmt = conn.createStatement();

            // Executing the SQL query
            ResultSet rs = stmt.executeQuery(query);

            // Processing the results
            while (rs.next()) {
                System.out.println("User found: " + rs.getString("username"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
