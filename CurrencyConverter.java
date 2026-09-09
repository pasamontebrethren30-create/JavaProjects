import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CurrencyConverter {

    // Free API endpoint that does not require an API key
    private static final String API_URL = "https://open.er-api.com/v6/latest/USD";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Java Currency Converter (USD Base) ---");
        System.out.print("Enter the amount in USD: ");
        
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.next();
        }
        double usdAmount = scanner.nextDouble();

        System.out.print("Enter the target currency code (e.g., EUR, GBP, JPY, PHP): ");
        String targetCurrency = scanner.next().toUpperCase();

        try {
            // Fetch live exchange rates
            String jsonResponse = fetchExchangeRates();
            
            // Extract the rate using robust regex matching
            double exchangeRate = extractRate(jsonResponse, targetCurrency);

            if (exchangeRate == -1) {
                System.out.println("Error: Currency code '" + targetCurrency + "' not found.");
            } else {
                double convertedAmount = usdAmount * exchangeRate;
                System.out.printf("\nSuccess! %.2f USD = %.2f %s (Rate: %.4f)\n", 
                                  usdAmount, convertedAmount, targetCurrency, exchangeRate);
            }

        } catch (Exception e) {
            System.out.println("Error fetching data: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    /**
     * Makes an HTTP GET request to fetch the latest USD exchange rates.
     */
    private static String fetchExchangeRates() throws Exception {
        URL url = URI.create(API_URL).toURL(); 
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        
        // FIX: Mimic a browser request so the API doesn't block or distort the Java data stream
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)");

        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            throw new RuntimeException("HttpResponseCode: " + responseCode);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        connection.disconnect();

        return response.toString();
    }

    /**
     * Parses the JSON string safely using regular expressions.
     */
    private static double extractRate(String json, String currency) {
        // Regex matches the pattern: "EUR":1.2345 or "EUR" : 1.2345
        String regex = "\"" + currency + "\"\\s*:\\s*([0-9.]+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(json);

        if (matcher.find()) {
            return Double.parseDouble(matcher.group(1));
        }

        return -1; // Currency code not found
    }
}
