import java.sql.*;

public class SQLInjectionVulnerableApp {

    public static void main(String[] args) {
        try {
            String userInput = "'; DROP TABLE users; --";
            String query = "SELECT * FROM users WHERE username = '" + userInput + "'";

            // Assuming there is a database connection established
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");
            Statement stmt = conn.createStatement();

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
