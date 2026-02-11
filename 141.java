Sure, here is an example of vulnerable Java code snippet that is susceptible to SQL Injection:

```java
import java.sql.*;

public class UserAuthenticator {

    public boolean authenticateUser(String username, String password) {
        String query = "SELECT * FROM Users WHERE username='" + username + "' AND password='" + password + "'";
        boolean isAuthenticated = false;

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                isAuthenticated = true;
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isAuthenticated;
    }

    public static void main(String[] args) {
        UserAuthenticator authenticator = new UserAuthenticator();
        
        // Simulating user input
        String userInputUsername = "admin' OR '1'='1";
        String userInputPassword = "password123";

        boolean isAuthenticated = authenticator.authenticateUser(userInputUsername, userInputPassword);
        
        if (isAuthenticated) {
            System.out.println("User authenticated successfully");
        } else {
