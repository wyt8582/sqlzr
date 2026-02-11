String userInput = "'; DROP TABLE users; --";
String query = "SELECT * FROM users WHERE username = '" + userInput + "'";

// Execute the SQL query
Statement statement = connection.createStatement();
ResultSet resultSet = statement.executeQuery(query);
