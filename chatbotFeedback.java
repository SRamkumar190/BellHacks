import java.util.Scanner;

public class FeedbackChatbot {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Feedback Chatbot!");
        System.out.println("Ask for feedback or type 'exit' to end the conversation.");

        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine().toLowerCase();

            if (userInput.equals("exit")) {
                System.out.println("Feedback Chatbot: Exiting the conversation. Goodbye!");
                break;
            }

            String feedback = getFeedback(userInput);
            System.out.println("Feedback Chatbot: " + feedback);
        }

        scanner.close();
    }

    private static String getFeedback(String userInput) {
        if (userInput.contains("performance") || userInput.contains("work")) {
            // You can implement more sophisticated logic for performance analysis
            return "Your performance seems satisfactory. Keep up the good work!";
        } else if (userInput.contains("active") || userInput.contains("activeness")) {
            // You can implement more sophisticated logic for assessing activeness
            return "You've been quite active. Great job!";
        } else {
            return "I'm not sure how to provide feedback for that. Ask about performance or activeness.";
        }
    }
}
