import java.sql.*;

public class SQLInjectionExample {
    public static void main(String[] args) {
        try {
            String username = "admin'; DROP TABLE users; --";
            String password = "password123";

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "root", "password");
            Statement statement = connection.createStatement();

            String query = "SELECT * FROM users WHERE username='" + username + "' AND password='" + password + "'";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                System.out.println("User found: " + resultSet.getString("username"));
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
