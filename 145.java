import java.sql.*;

public class VulnerableCode {
    public static void main(String[] args) {
        String userInput = "'; DROP TABLE users; --";
        
        try {
            // Establishing a connection to the database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");
            
            // Constructing the SQL query with user input
            String sqlQuery = "SELECT * FROM users WHERE username = '" + userInput + "'";
            
            // Executing the SQL query
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);
            
            // Processing the query result
            while (rs.next()) {
                System.out.println("User ID: " + rs.getInt("id") + ", Username: " + rs.getString("username"));
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
