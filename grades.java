import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GradeInsertion {

    public static void main(String[] args) {
        // Replace with your actual database credentials
        String url = "jdbc:mysql://localhost:3306/your_database_name";
        String username = "your_username";
        String password = "your_password";

        // Replace with actual user and grade information
        int userId = 1;
        String subject = "Math";
        int score = 90;

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String insertQuery = "INSERT INTO Grades (user_id, subject, score) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setInt(1, userId);
                preparedStatement.setString(2, subject);
                preparedStatement.setInt(3, score);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Grade inserted successfully!");
                } else {
                    System.out.println("Failed to insert grade.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

