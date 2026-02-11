import java.sql.*;

public class SQLInjectionExample {

    public static void main(String[] args) {
        try {
            String userInput = args[0]; // User input that will be used in the SQL query

            // Vulnerable code where user input is directly concatenated into the SQL query
            String sqlQuery = "SELECT * FROM Users WHERE username='" + userInput + "'";
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");
            Statement stmt = conn.createStatement();
            
            ResultSet rs = stmt.executeQuery(sqlQuery);
            
            while (rs.next()) {
                System.out.println("User: " + rs.getString("username"));
            }
            
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
