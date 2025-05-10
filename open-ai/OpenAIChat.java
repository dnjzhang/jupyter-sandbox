import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class OpenAIChat {

    private static final String API_KEY = "YOUR_API_KEY"; // replace with your OpenAI API key
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";

    public static void main(String[] args) {
        try {
            String response = callOpenAI("Tell me a joke");
            System.out.println("Response from OpenAI: " + response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String callOpenAI(String prompt) throws Exception {
        // Create the connection
        URL url = new URL(API_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        // Set request method and headers
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "Bearer " + API_KEY);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        // Create the JSON payload
        String jsonInputString = String.format(
                "{\"model\": \"gpt-3.5-turbo\", \"messages\": [{\"role\": \"user\", \"content\": \"%s\"}]}",
                prompt);

        // Send the request
        try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
            wr.writeBytes(jsonInputString);
            wr.flush();
        }

        // Get the response
        int responseCode = conn.getResponseCode();
        StringBuilder response = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        }

        // Close the connection
        conn.disconnect();

        // Return the response
        if (responseCode == HttpURLConnection.HTTP_OK) {
            return response.toString();
        } else {
            throw new Exception("Error: " + responseCode + " - " + response.toString());
        }
    }
}
