String username = request.getParameter("username");
String password = request.getParameter("password");

String query = "SELECT * FROM users WHERE username='" + username + "' AND password='" + password + "'";
Statement statement = connection.createStatement();
ResultSet resultSet = statement.executeQuery(query);

if (resultSet.next()) {
    // User authenticated successfully
} else {
    // Authentication failed
}
