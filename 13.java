import java.sql.*;

public class SQLInjectionExample {
    public static void main(String[] args) {
        try {
            // Simulating user input
            String userInput = "'; DROP TABLE users; --";

            // Establishing a database connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");
            
            // Creating a SQL statement using the user input (vulnerable code)
            String query = "SELECT * FROM users WHERE username = '" + userInput + "'";
            
            // Executing the SQL query
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Displaying the results
            while (rs.next()) {
                System.out.println("Username: " + rs.getString("username"));
                System.out.println("Email: " + rs.getString("email"));
            }

            // Closing resources
            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
