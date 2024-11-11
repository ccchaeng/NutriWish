package com.example.nutriwish;

import java.io.Serializable;

public class Supplement implements Serializable {
    private String name;
    private String benefits;
    private String usage;
    private String precautions;
    private boolean isFavorite; // Field to indicate if this supplement is marked as favorite

    // Constructor with all fields
    public Supplement(String name, String benefits, String usage, String precautions) {
        this.name = name;
        this.benefits = benefits;
        this.usage = usage;
        this.precautions = precautions;
        this.isFavorite = false; // Default value is false
    }

    // Constructor with only the name field (for potential future uses)
    public Supplement(String name) {
        this(name, "", "", "");
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public String getBenefits() {
        return benefits;
    }

    public String getUsage() {
        return usage;
    }

    public String getPrecautions() {
        return precautions;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    // Method to toggle favorite status
    public void toggleFavorite() {
        this.isFavorite = !this.isFavorite;
    }

    // Override toString() to return the supplement name for display purposes
    @Override
    public String toString() {
        return name;
    }
}