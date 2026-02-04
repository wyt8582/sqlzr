Sure, here is an example of Java code snippet vulnerable to SQL Injection:

```java
import java.sql.*;

public class UserAuthentication {

    public boolean authenticateUser(String username, String password) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");
            stmt = conn.createStatement();
            String query = "SELECT * FROM users WHERE username='" + username + "' AND password='" + password + "'";
            rs = stmt.executeQuery(query);

            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        UserAuthentication auth = new UserAuthentication();
        
        // This input can be manipulated to perform SQL Injection
        String userInputUsername = "admin'; --";
        String userInputPassword = "password123";

        boolean isAuthenticated = auth
