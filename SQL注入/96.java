import java.sql.*;

public class SQLInjectionExample {
    public static void main(String[] args) {
        String userInput = "'; DROP TABLE users; --";
        String query = "SELECT * FROM users WHERE username = '" + userInput + "'";

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()) {
                System.out.println(resultSet.getString("username"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
