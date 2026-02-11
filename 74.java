import java.sql.*;

public class SQLInjectionVulnerableCode {

    public static void main(String[] args) {
        try {
            String userInput = "1 OR 1=1"; // Simulated user input for SQL Injection
            String query = "SELECT * FROM users WHERE id=" + userInput; // Vulnerable query construction

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                System.out.println("User ID: " + rs.getInt("id") + ", Username: " + rs.getString("username"));
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
