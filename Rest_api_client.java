
/**
 * This program demonstrates consuming a public REST API in Java.
 * It fetches weather data from a public API and displays the data in a structured format.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class Rest_api_client {

    public static void main(String[] args) {
        String apiKey = "your_api_key_here"; // Replace with your API key
        String city = "London"; // Replace with your desired city
        String urlString = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey + "&units=metric";

        try {
            // Send HTTP GET request
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Check response code
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) { // Success
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Parse and display JSON response
                parseAndDisplayWeatherData(response.toString());
            } else {
                System.out.println("Error: Unable to fetch data. Response code: " + responseCode);
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    /**
     * Parses the JSON response and displays weather data in a structured format.
     * @param jsonResponse The JSON response as a string.
     */
    private static void parseAndDisplayWeatherData(String jsonResponse) {
        JSONObject jsonObject = new JSONObject(jsonResponse);

        // Extract data
        String cityName = jsonObject.getString("name");
        JSONObject main = jsonObject.getJSONObject("main");
        double temperature = main.getDouble("temp");
        int humidity = main.getInt("humidity");
        JSONObject weather = jsonObject.getJSONArray("weather").getJSONObject(0);
        String description = weather.getString("description");

        // Display data
        System.out.println("Weather Data for " + cityName + ":");
        System.out.println("Temperature: " + temperature + "°C");
        System.out.println("Humidity: " + humidity + "%");
        System.out.println("Description: " + description);
    }
}
