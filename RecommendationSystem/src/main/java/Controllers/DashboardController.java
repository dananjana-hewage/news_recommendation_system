package Controllers;

import Utils.APIClient;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Article;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;
import java.util.List;

public class  DashboardController {

    @FXML
    public Button setPreferencesBtn;
    @FXML
    private ListView<String> articlesListView; // List view to display article titles
    @FXML
    private ImageView articleImageView; // Image view to display the article image
    @FXML

    private Label articleTitleLabel; // Label to display article title
    @FXML
    private Label articleDescriptionLabel; // Label to display article description

    private APIClient apiClient = new APIClient();

    public void initialize() {
        // This can be used to load articles or categories when the dashboard starts
        loadArticles("business");
    }

    // Set preferences method triggered by a button click
    @FXML
    private void setPreferencesOnAction() {

        try {
            // Load Preference.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("com/example/recommendationsystem/preferences.fxml"));
            Parent preferenceRoot = loader.load();

            // Get the current stage and set the new scene
            Stage stage = (Stage) setPreferencesBtn.getScene().getWindow();
            stage.setScene(new Scene(preferenceRoot));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//

    // Load articles based on selected category
    private void loadArticles(String category) {
        List<Article> articles = apiClient.fetchArticles(category);

        // Populate ListView with article titles
        articlesListView.getItems().clear(); // Clear existing items
        for (Article article : articles) {
            articlesListView.getItems().add(article.getTitle());
        }

        // Add a listener to update the UI when an article is selected
        articlesListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // Find the article that matches the selected title
            for (Article article : articles) {
                if (article.getTitle().equals(newValue)) {
                    // Display article details
                    articleTitleLabel.setText(article.getTitle());
                    articleDescriptionLabel.setText(article.getDescription());

                    // Load the article image
                    if (article.getImageUrl() != null && !article.getImageUrl().isEmpty()) {
                        Image image = new Image(article.getImageUrl());
                        articleImageView.setImage(image);
                    }
                }
            }
        });
    }

    // Method to show info alerts
    private void showInfoAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Method to show error alerts
    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
