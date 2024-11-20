package com.example.nutriwish;

import android.util.Log;

import java.io.Serializable;

public class Supplement implements Serializable {
    private String name;
    private String benefits;
    private String usage;
    private String precautions;
    private boolean favorite; // Indicates if this supplement is marked as favorite

    // Constructor with all fields
    public Supplement(String name, String benefits, String usage, String precautions) {
        this.name = name;
        this.benefits = benefits;
        this.usage = usage;
        this.precautions = precautions;
        this.favorite = false;
    }

    // Constructor with only the name field (for flexibility in future uses)
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

    public boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
        Log.d("test", "setFavorite : " + Boolean.toString(this.favorite));
    }

//    // Method to toggle favorite status
//    public void toggleFavorite() {
//        this.isFavorite = !this.isFavorite;
//    }

    // Override toString() for displaying the supplement name in lists
    @Override
    public String toString() {
        return name;
    }
}
