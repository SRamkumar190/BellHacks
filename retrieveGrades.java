import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GradeRetrieval {

    public static void main(String[] args) {
        // Replace with your actual database credentials
        String url = "jdbc:mysql://localhost:3306/your_database_name";
        String username = "your_username";
        String password = "your_password";

        // Replace with actual user information
        int userId = 1;

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String selectQuery = "SELECT * FROM Grades WHERE user_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setInt(1, userId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int gradeId = resultSet.getInt("grade_id");
                        String subject = resultSet.getString("subject");
                        int score = resultSet.getInt("score");

                        System.out.println("Grade ID: " + gradeId + ", Subject: " + subject + ", Score: " + score);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
