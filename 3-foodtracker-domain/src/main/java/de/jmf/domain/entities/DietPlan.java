package de.jmf.domain.entities;

import java.util.ArrayList;
import java.util.List;

public class DietPlan {
    private int id;
    private String name;
    private List<Meal> meals;

    public DietPlan(int id, String name) {
        this.id = id;
        this.name = name;
        this.meals = new ArrayList<>();
    }

    public void addMeal(Meal meal) {
        this.meals.add(meal);
    }

    public void removeMeal(Meal meal) {
        this.meals.remove(meal);
    }

    // Getters and Setters


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Meal> getMeals() {
        return meals;
    }
}

