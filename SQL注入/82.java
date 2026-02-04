import java.sql.*;

public class SQLInjectionVulnerable {

    public static void main(String[] args) {
        String userInput = "admin'; DROP TABLE users;--"; // Malicious user input

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");
            Statement stmt = conn.createStatement();

            String query = "SELECT * FROM users WHERE username='" + userInput + "'";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                System.out.println(rs.getString("username") + " - " + rs.getString("email"));
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
