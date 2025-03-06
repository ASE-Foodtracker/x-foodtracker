package de.jmf.application.repositories;

import java.time.LocalDate;
import java.util.List;

import de.jmf.domain.entities.Meal;
import de.jmf.domain.entities.NutritionLog;
import de.jmf.domain.entities.WeightLog;
import de.jmf.domain.valueobjects.ProgressTracker;
import de.jmf.domain.valueobjects.Weight;

public class ProgressRepository {
    private final ProgressTracker progressTracker;

    public ProgressRepository() {
        this.progressTracker = new ProgressTracker();
    }

    // Weight

    public void loadWeight(List<WeightLog> weightLogs) {
        for (WeightLog log : weightLogs) {
            this.progressTracker.addWeightLog(log);
        }
    }

    public void addWeight(Weight weight) {
        this.progressTracker.addWeightLog(new WeightLog(LocalDate.now(), weight));
    }

    // Meal

    public void saveMeal(Meal meal, LocalDate date) {
        // Save Meal
        if (date == null) {
            date = LocalDate.now();
        }
        NutritionLog log = new NutritionLog(date, meal);
        this.progressTracker.addNutritionLog(log);
    }

    public List<NutritionLog> getNutritionLogs() {
        return this.progressTracker.getNutritionLogs();
    }

    public void removeMeal(NutritionLog meal) {
        this.progressTracker.removeNutritionLog(meal);
    }

    // ProgressTracker

    public ProgressTracker getProgress() {
        return this.progressTracker;
    }
}
