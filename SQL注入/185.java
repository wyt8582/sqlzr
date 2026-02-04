import java.sql.*;

public class UserLogin {
    public static void main(String[] args) {
        String username = args[0];
        String password = args[1];

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "password");
            Statement stmt = conn.createStatement();

            String query = "SELECT * FROM users WHERE username='" + username + "' AND password='" + password + "'";
            ResultSet rs = stmt.executeQuery(query);

            if(rs.next()) {
                System.out.println("Login successful!");
            } else {
                System.out.println("Login failed!");
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
