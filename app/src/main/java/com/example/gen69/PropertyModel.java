package com.example.gen69;

public class PropertyModel {
    private String description;
    private String location;
    private String imageUrl;
    private String type;
    private String userId;

    // 🔹 Empty constructor (needed by Firebase)
    public PropertyModel() {}

    // 🔹 Optional: full constructor
    public PropertyModel(String description, String location, String imageUrl, String type, String userId) {
        this.description = description;
        this.location = location;
        this.imageUrl = imageUrl;
        this.type = type;
        this.userId = userId;
    }

    // 🔹 Getters
    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getType() {
        return type;
    }

    public String getUserId() {
        return userId;
    }

    // 🔹 Setters (optional but recommended)
    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

