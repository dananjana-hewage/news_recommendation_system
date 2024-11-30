package models;

public class Article {
    private String title;
    private String description;
    private String content;
    private String imageUrl;
    private String url;

    // Constructor
    public Article(String title, String description, String content, String imageUrl, String url) {
        this.title = title;
        this.description = description;
        this.content = content;
        this.imageUrl = imageUrl;
        this.url = url;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getContent() {
        return content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getUrl() {
        return url;
    }
}
