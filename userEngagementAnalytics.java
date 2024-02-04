import java.util.HashMap;
import java.util.Map;

public class UserEngagementTracker {

    private Map<String, UserEngagementData> userEngagementMap;

    public UserEngagementTracker() {
        this.userEngagementMap = new HashMap<>();
    }

    public void recordEngagement(String userID, boolean isFocused) {
        UserEngagementData userData = userEngagementMap.getOrDefault(userID, new UserEngagementData());
        userData.addEngagement(isFocused);
        userEngagementMap.put(userID, userData);
    }

    public void printUserEngagementData(String userID) {
        if (userEngagementMap.containsKey(userID)) {
            UserEngagementData userData = userEngagementMap.get(userID);
            System.out.println("User " + userID + " Engagement Data:");
            System.out.println("Total Sessions: " + userData.getTotalSessions());
            System.out.println("Total Focused Sessions: " + userData.getFocusedSessions());
            System.out.println("Average Focus Percentage: " + userData.getAverageFocusPercentage() + "%");
        } else {
            System.out.println("User " + userID + " not found in engagement data.");
        }
    }

    public static void main(String[] args) {
        UserEngagementTracker tracker = new UserEngagementTracker();

        // Simulate user engagement data
        String userID = "user123";
        tracker.recordEngagement(userID, true);
        tracker.recordEngagement(userID, false);
        tracker.recordEngagement(userID, true);

        // Print user engagement data
        tracker.printUserEngagementData(userID);
    }
}

class UserEngagementData {
    private int totalSessions;
    private int focusedSessions;

    public UserEngagementData() {
        this.totalSessions = 0;
        this.focusedSessions = 0;
    }

    public void addEngagement(boolean isFocused) {
        totalSessions++;
        if (isFocused) {
            focusedSessions++;
        }
    }

    public int getTotalSessions() {
        return totalSessions;
    }

    public int getFocusedSessions() {
        return focusedSessions;
    }

    public double getAverageFocusPercentage() {
        return (double) focusedSessions / totalSessions * 100;
    }
}

