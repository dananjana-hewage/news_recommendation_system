package views;

import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Article;

public class ArticleDetailsPage {
    public ArticleDetailsPage(Article article) {
        Label titleLabel = new Label(article.getTitle());
        Label contentLabel = new Label(article.getContent());
        ImageView articleImage = new ImageView(new Image(article.getImageUrl()));
        Hyperlink urlLink = new Hyperlink("Read more: " + article.getUrl());
        //urlLink.setOnAction(e -> getHostServices().showDocument(article.getUrl()));

        VBox vbox = new VBox(articleImage, titleLabel, contentLabel, urlLink);
        Scene scene = new Scene(vbox, 600, 400);

        Stage stage = new Stage();
        stage.setTitle("Article Details");
        stage.setScene(scene);
        stage.show();
    }
}
