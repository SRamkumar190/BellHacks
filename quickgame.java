import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Icebreaker {

    // Map to store user scores
    private static Map<String, Integer> userScores = new HashMap<>();

    // Method to simulate an icebreaker game
    public static int playIcebreaker(String userID) {
        // Simulate a game by generating a random score for the user
        Random random = new Random();
        int score = random.nextInt(100);

        // Store the user's score in the map
        userScores.put(userID, score);

        return score;
    }

    // Method to find the best matches based on scores
    public static Map<String, Integer> findBestMatches(String userID) {
        Map<String, Integer> bestMatches = new HashMap<>();

        // Calculate the difference in scores and find the closest matches
        userScores.entrySet().stream()
                .filter(entry -> !entry.getKey().equals(userID))
                .sorted(Map.Entry.comparingByValue())
                .limit(4)
                .forEach(entry -> bestMatches.put(entry.getKey(), entry.getValue()));

        return bestMatches;
    }

    public static void main(String[] args) {
        // Simulate an icebreaker game for a student with ID "student123"
        String userID = "student123";
        int userScore = playIcebreaker(userID);
        System.out.println("Student " + userID + " scored: " + userScore);

        // Find the best matches for the student
        Map<String, Integer> bestMatches = findBestMatches(userID);
        System.out.println("Best matches:");
        for (Map.Entry<String, Integer> entry : bestMatches.entrySet()) {
            System.out.println("Student " + entry.getKey() + " with score: " + entry.getValue());
        }
    }
}
