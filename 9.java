String userInput = "'; DROP TABLE users; --"; // User input with malicious SQL injection
String query = "SELECT * FROM users WHERE username = '" + userInput + "'";

// Execute the SQL query
Statement statement = connection.createStatement();
ResultSet resultSet = statement.executeQuery(query);
