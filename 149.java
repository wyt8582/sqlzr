import java.sql.*;

public class SQLInjectionExample {
    public static void main(String[] args) {
        try {
            String userInput = "'; DROP TABLE users; --"; // This is the user input that contains malicious SQL code
            String query = "SELECT * FROM users WHERE username = '" + userInput + "'";

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                System.out.println(rs.getString("username"));
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
