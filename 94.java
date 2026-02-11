String userInput = "'; DROP TABLE users; --"; // User input with malicious SQL injection payload

String query = "SELECT * FROM users WHERE username = '" + userInput + "'";

Statement statement = connection.createStatement();
ResultSet resultSet = statement.executeQuery(query);
