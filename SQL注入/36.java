import java.sql.*;

public class SQLInjectionVulnerable {
    public static void main(String[] args) {
        try {
            String username = args[0]; // User input
            String password = args[1]; // User input

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "root", "password");

            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM users WHERE username='" + username + "' AND password='" + password + "'";
            
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                System.out.println("Logged in as: " + rs.getString("username"));
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
