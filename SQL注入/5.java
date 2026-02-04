import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SQLInjectionVulnerable {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a username: ");
        String username = scanner.nextLine();
        
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "root", "password");
            Statement stmt = conn.createStatement();
            
            String query = "SELECT * FROM users WHERE username='" + username + "'";
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()) {
                System.out.println("User ID: " + rs.getInt("id") + ", Username: " + rs.getString("username"));
            }
            
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
