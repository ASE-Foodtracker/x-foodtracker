package de.jmf.domain.entities;

import java.util.List;

public class Meal {
    private String name;
    private int calories;
    private int protein;
    private List<String> ingredients;

    public Meal(String name, int protein, int calories) {
        this.name = name;
        this.protein = protein;
        this.calories = calories;
    }

    public int calculateTotalCalories() {
        return this.calories;
    }

    // Getters and Setters


    public String getName() {
        return name;
    }

    public int getCalories() {
        return calories;
    }

    public int getProtein() {return protein;}
}