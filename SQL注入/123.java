import java.sql.*;

public class SQLInjectionVulnerable {
    public static void main(String[] args) {
        try {
            String userInput = "admin'; DROP TABLE users; --";
            String query = "SELECT * FROM users WHERE username = '" + userInput + "'";

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "root", "password");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                System.out.println(resultSet.getString("username"));
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
