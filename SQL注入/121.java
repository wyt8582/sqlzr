import java.sql.*;

public class SQLInjectionVulnerable {
    public static void main(String[] args) {
        try {
            String userInput = args[0]; // Assume userInput is directly taken from user input

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "username", "password");
            Statement stmt = conn.createStatement();

            String query = "SELECT * FROM users WHERE username='" + userInput + "'";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                System.out.println("User found: " + rs.getString("username"));
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
