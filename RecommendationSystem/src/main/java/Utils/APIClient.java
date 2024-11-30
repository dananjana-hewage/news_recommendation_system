package Utils;

import models.Article;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


















































































































import java.util.ArrayList;
import java.util.List;

public class APIClient {

    private static final String BASE_URL = "https://newsapi.org/v2/top-headlines";
    private static final String API_KEY = "37aa9c63ea4c47d48ec3302481f1b760"; // Replace with your API key

    // Fetch categories dynamically by getting headlines from various sections
    public List<String> fetchCategories() {
        List<String> categories = new ArrayList<>();

        // List of category endpoints for NewsAPI (predefined sections)
        String[] categoryNames = {"business", "entertainment", "health", "science", "sports", "technology"};

        // Fetch categories dynamically
        for (String category : categoryNames) {
            String urlString = String.format("%s?category=%s&apiKey=%s", BASE_URL, category, API_KEY);
            HttpURLConnection connection = null;

            try {
                // Create a URL object
                URL url = new URL(urlString);

                // Open a connection
                connection = (HttpURLConnection) url.openConnection();

                // Set request method to GET
                connection.setRequestMethod("GET");

                // Check the response code
                int responseCode = connection.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) { // If response is successful
                    // Read the response
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();

                    // Parse JSON response
                    JSONObject jsonResponse = new JSONObject(response.toString());
                    JSONArray articles = jsonResponse.getJSONArray("articles");

                    if (articles.length() > 0) {
                        categories.add(category); // Add category if there are articles
                    }
                } else {
                    System.out.println("Error fetching data for category: " + category);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
        }

        return categories;
    }

    // Fetch articles for a specific category
    public List<Article> fetchArticles(String category) {
        List<Article> articles = new ArrayList<>();

        String urlString = String.format("%s?category=%s&apiKey=%s", BASE_URL, category, API_KEY);
        HttpURLConnection connection = null;

        try {
            // Create a URL object
            URL url = new URL(urlString);

            // Open a connection
            connection = (HttpURLConnection) url.openConnection();

            // Set request method to GET
            connection.setRequestMethod("GET");

            // Check the response code
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) { // If response is successful
                // Read the response
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Parse JSON response
                JSONObject jsonResponse = new JSONObject(response.toString());
                JSONArray articlesJson = jsonResponse.getJSONArray("articles");

                // Loop through the articles and create Article objects
                for (int i = 0; i < articlesJson.length(); i++) {
                    JSONObject articleJson = articlesJson.getJSONObject(i);

                    String title = articleJson.optString("title", "No title available");
                    // Safely retrieve the description field
                    String description = articleJson.optString("description", "No description available");

                    // Safely retrieve the content field
                    String content = articleJson.has("content") ? articleJson.optString("content", "No content available") : "No content available";

                    String imageUrl = articleJson.optString("urlToImage", ""); // Handle missing image URL
                    String urlToArticle = articleJson.optString("url", ""); // Handle missing article URL

                    // Create an Article object and add it to the list
                    Article article = new Article(title, description, content, imageUrl, urlToArticle);
                    articles.add(article);
                }
            } else {
                System.out.println("Error fetching data for category: " + category);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        return articles;
    }
}
