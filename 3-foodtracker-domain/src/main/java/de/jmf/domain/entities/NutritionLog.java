package de.jmf.domain.entities;

import java.time.LocalDate;

public class NutritionLog {
    private LocalDate date;
    private Meal meal;

    public NutritionLog(LocalDate date, Meal meal) {
        this.date = date;
        this.meal = meal;
    }

    // Getters and Setters

    public LocalDate getDate() {
        return date;
    }

    public Meal getMeal() {
        return meal;
    }
}
