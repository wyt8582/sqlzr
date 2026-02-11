import java.sql.*;

public class SQLInjectionExample {

    public static void main(String[] args) {
        try {
            // Assume that the user input is directly concatenated into the SQL query
            String userInput = "admin'; DROP TABLE users; --";
            String query = "SELECT * FROM users WHERE username = '" + userInput + "'";

            // Connect to database and execute query
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "username", "password");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Process the result set
            while (rs.next()) {
                System.out.println(rs.getString("username"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
