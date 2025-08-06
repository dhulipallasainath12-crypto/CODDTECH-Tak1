import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SimpleWeatherApp {
    public static void main(String[] args) {
        String apiKey = "YOUR_API_KEY"; // Replace with your OpenWeatherMap API key
        String city = "Tokyo";
        String urlString = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey + "&units=metric";

        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Simple JSON parsing (for basic display)
            String json = response.toString();
            String weather = json.split("\"description\":\"")[1].split("\"")[0];
            String temp = json.split("\"temp\":")[1].split(",")[0];

            System.out.println("Current weather in " + city + ":");
            System.out.println("Temperature: " + temp + "°C");
            System.out.println("Conditions: " + weather);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
