package de.jmf.domain.entities;

import java.time.LocalDate;

public class NutritionLog {
    private int id;
    private LocalDate date;
    private Meal meal;

    public NutritionLog(int id, LocalDate date, Meal meal) {
        this.id = id;
        this.date = date;
        this.meal = meal;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Meal getMeal() {
        return meal;
    }
}