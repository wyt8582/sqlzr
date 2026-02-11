import java.sql.*;

public class SQLInjectionExample {

    public static void main(String[] args) {
        // Simulating user input (which should not be trusted)
        String userInput = "'; DROP TABLE users; --";
        
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");
            Statement stmt = conn.createStatement();
            
            // Vulnerable code - directly concatenating user input into the SQL query
            String query = "SELECT * FROM users WHERE username = '" + userInput + "'";
            
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                System.out.println(rs.getString("username"));
            }
            
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
