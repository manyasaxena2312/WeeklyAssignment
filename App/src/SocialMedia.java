import java.util.HashMap;

public class SocialMedia {
    private HashMap<String, Integer> usernameMap = new HashMap<>();
    private HashMap<String, Integer> attemptFrequency = new HashMap<>();

    // Check if username is available
    public boolean checkAvailability(String username) {
        attemptFrequency.put(username, attemptFrequency.getOrDefault(username, 0) + 1);
        return !usernameMap.containsKey(username);
    }

    // Register a username
    public void registerUsername(String username, int userId) {
        usernameMap.put(username, userId);
    }

    // Suggest similar usernames
    public String[] suggestAlternatives(String username) {
        String[] suggestions = new String[] {
                username + "1",
                username + "2",
                username.replaceAll(" ", ".")
        };
        return suggestions;
    }

    // Get most attempted username
    public String getMostAttempted() {
        String mostAttempted = null;
        int maxAttempts = 0;
        for (String user : attemptFrequency.keySet()) {
            int attempts = attemptFrequency.get(user);
            if (attempts > maxAttempts) {
                maxAttempts = attempts;
                mostAttempted = user;
            }
        }
        return mostAttempted;
    }

    public static void main(String[] args) {
        SocialMedia checker = new SocialMedia();
        checker.registerUsername("john_doe", 1);

        System.out.println(checker.checkAvailability("john_doe")); // false
        System.out.println(checker.checkAvailability("jane_smith")); // true
        System.out.println("Suggestions: " + String.join(", ", checker.suggestAlternatives("john_doe")));
        System.out.println("Most attempted: " + checker.getMostAttempted());
    }
}