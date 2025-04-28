package de.jmf.domain.entities;

import java.time.LocalDate;

public class NutritionLog {
    private LocalDate date;
    private Meal meal;

    public NutritionLog(LocalDate date, Meal meal) {
        this.date = date;
        this.meal = meal;
    }

    public LocalDate getDate() {
        return date;
    }

    public Meal getMeal() {
        return meal;
    }
}
