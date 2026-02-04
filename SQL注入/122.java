import java.sql.*;

public class SQLInjectionExample {

    public static void main(String[] args) {
        String username = args[0]; // Simulating user input
        
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");
            Statement stmt = conn.createStatement();
            
            String query = "SELECT * FROM users WHERE username='" + username + "'";
            
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()) {
                System.out.println("User ID: " + rs.getInt("id") + ", Username: " + rs.getString("username"));
            }
            
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
