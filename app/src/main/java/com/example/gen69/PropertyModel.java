package com.example.gen69;

public class PropertyModel {

    private String id;            // 🔹 Firestore document ID
    private String description;
    private String location;
    private String imageUrl;
    private String type;
    private String userId;

    public PropertyModel() {
        // Required empty constructor for Firestore
    }

    public PropertyModel(String id, String description, String location, String imageUrl, String type, String userId) {
        this.id = id;
        this.description = description;
        this.location = location;
        this.imageUrl = imageUrl;
        this.type = type;
        this.userId = userId;
    }

    // 🔹 Add ID getter & setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Other getters & setters
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
