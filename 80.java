String userInput = "'; DROP TABLE users; --";
String query = "SELECT * FROM users WHERE username = '" + userInput + "'";

Statement statement = connection.createStatement();
ResultSet resultSet = statement.executeQuery(query);

while (resultSet.next()) {
    System.out.println(resultSet.getString("username"));
}
