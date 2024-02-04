import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSetup {

    public static void main(String[] args) {
        // Establish a connection to the MySQL database
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database_name", "your_username", "your_password")) {
            
            // Create a Statement object for executing SQL queries
            try (Statement statement = connection.createStatement()) {

                // Define your SQL query to create a table
                String createTableQuery = "CREATE TABLE IF NOT EXISTS your_table_name ("
                        + "id INT AUTO_INCREMENT PRIMARY KEY,"
                        + "name VARCHAR(255),"
                        + "age INT)";

                // Execute the query
                statement.executeUpdate(createTableQuery);

                System.out.println("Table created successfully!");

            } catch (SQLException createTableException) {
                createTableException.printStackTrace();
            }

        } catch (SQLException connectionException) {
            connectionException.printStackTrace();
        }
    }
}

