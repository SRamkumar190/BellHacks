import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChatSpaceBackend {

    // Replace these with your actual database credentials
    private static final String URL = "jdbc:mysql://localhost:3306/your_database_name";
    private static final String USERNAME = "your_username";
    private static final String PASSWORD = "your_password";

    public static void main(String[] args) {
        // Initialize the chat space
        ChatSpace chatSpace = new ChatSpace();

        // Example: Add messages to the chat space
        chatSpace.addMessage("User1", "Hello, how are you?");
        chatSpace.addMessage("User2", "I'm good, thank you!");

        // Example: Retrieve and display messages
        List<ChatMessage> messages = chatSpace.getMessages();
        for (ChatMessage message : messages) {
            System.out.println(message);
        }
    }

    static class ChatSpace {

        private List<ChatMessage> messages;

        public ChatSpace() {
            messages = new ArrayList<>();
        }

        public void addMessage(String username, String text) {
            ChatMessage message = new ChatMessage(username, text);
            messages.add(message);

            // Save the message to the database
            saveMessageToDatabase(message);
        }

        public List<ChatMessage> getMessages() {
            // Retrieve messages from the database
            return retrieveMessagesFromDatabase();
        }

        private void saveMessageToDatabase(ChatMessage message) {
            try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
                String insertQuery = "INSERT INTO ChatMessages (username, text) VALUES (?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                    preparedStatement.setString(1, message.getUsername());
                    preparedStatement.setString(2, message.getText());

                    preparedStatement.executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        private List<ChatMessage> retrieveMessagesFromDatabase() {
            List<ChatMessage> result = new ArrayList<>();

            try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
                String selectQuery = "SELECT * FROM ChatMessages";
                try (Statement statement = connection.createStatement()) {
                    try (ResultSet resultSet = statement.executeQuery(selectQuery)) {
                        while (resultSet.next()) {
                            String username = resultSet.getString("username");
                            String text = resultSet.getString("text");
                            result.add(new ChatMessage(username, text));
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return result;
        }
    }

    static class ChatMessage {

        private String username;
        private String text;

        public ChatMessage(String username, String text) {
            this.username = username;
            this.text = text;
        }

        public String getUsername() {
            return username;
        }

        public String getText() {
            return text;
        }

        @Override
        public String toString() {
            return username + ": " + text;
        }
    }
}
