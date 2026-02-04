import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SQLInjectionVulnerableCode {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a username to search for: ");
            String userInput = scanner.nextLine();

            // Vulnerable code: user input directly concatenated into SQL query
            String sqlQuery = "SELECT * FROM users WHERE username = '" + userInput + "'";
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "root", "password");
            Statement stmt = conn.createStatement();
            
            ResultSet rs = stmt.executeQuery(sqlQuery);
            
            while(rs.next()) {
                System.out.println("User found: " + rs.getString("username"));
            }
            
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
