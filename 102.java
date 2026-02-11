import java.sql.*;

public class VulnerableCode {

    public void searchUser(String username) {
        String query = "SELECT * FROM Users WHERE username = '" + username + "'";

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "root", "password");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                System.out.println("User ID: " + resultSet.getInt("id") + ", Username: " + resultSet.getString("username"));
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        VulnerableCode code = new VulnerableCode();
        String userInput = "admin' OR '1'='1";
        code.searchUser(userInput);
    }
}
