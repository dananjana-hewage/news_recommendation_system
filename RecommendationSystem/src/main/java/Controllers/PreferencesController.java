package Controllers;

import Utils.APIClient;
import database.DatabaseManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PreferencesController {

    @FXML
    public Button savePreferencesBtn;

    @FXML
    public AnchorPane preferencePane;
    @FXML
    private VBox categoryContainer;

    //-----------------------------
    public void initialize() {
        // Fetch categories from the API
        List<String> categories = fetchCategories();

        // Populate VBox with checkboxes
        for (String category : categories) {
            CheckBox checkBox = new CheckBox(category);
            categoryContainer.getChildren().add(checkBox);
        }
    }

    // Mock API call to fetch categories
    private List<String> fetchCategories() {
        // Replace with actual API call
        return Arrays.asList("Business", "Entertainment", "General", "Health", "Science", "Sports", "Technology");
    }

    //------------------------------

//    private APIClient apiClient = new APIClient();
//    private DatabaseManager databaseManager = new DatabaseManager();
//
//    private int currentUserId = 1; // Example user ID; replace with actual logic for getting the logged-in user ID.
//
//    // Load categories dynamically
//    public void loadCategories() {
//        List<String> categories = apiClient.fetchCategories();
//        categoryContainer.getChildren().clear();
//
//        for (String category : categories) {
//            CheckBox checkBox = new CheckBox(category);
//            categoryContainer.getChildren().add(checkBox);
//        }
   // }

//
//    // Helper method to show alerts
//    private void showAlert(String title, String message) {
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle(title);
//        alert.setHeaderText(null);
//        alert.setContentText(message);
//        alert.showAndWait();
//    }
//
//    public void savePreferencesOnAction(ActionEvent actionEvent) {
//
//        List<String> selectedCategories = new ArrayList<>();
//
//        // Get selected categories from the checkboxes
//        categoryContainer.getChildren().forEach(node -> {
//            if (node instanceof CheckBox) {
//                CheckBox checkBox = (CheckBox) node;
//                if (checkBox.isSelected()) {
//                    selectedCategories.add(checkBox.getText());
//                }
//            }
//        });
//
//        if (selectedCategories.isEmpty()) {
//            showAlert("Error", "Please select at least one category.");
//            return;
//        }
//
//        // Save to the database
//        boolean isSaved = databaseManager.savePreferences(currentUserId, selectedCategories);
//        if (isSaved) {
//            showAlert("Success", "Preferences saved successfully!");
//        } else {
//            showAlert("Error", "Failed to save preferences. Please try again.");
//        }
//    }
}
