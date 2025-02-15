// VO: Is a snapshot of progress at a point in time.

package de.jmf.domain.valueobjects;

import de.jmf.domain.entities.ExerciseLog;
import de.jmf.domain.entities.NutritionLog;
import de.jmf.domain.entities.WeightLog;

import java.util.ArrayList;
import java.util.List;

public class ProgressTracker {
    private int id;
    private List<WeightLog> weightLogs;
    private List<ExerciseLog> exerciseLogs;
    private List<NutritionLog> nutritionLogs;

    public ProgressTracker(int id) {
        this.id = id;
        this.weightLogs = new ArrayList<>();
        this.exerciseLogs = new ArrayList<>();
        this.nutritionLogs = new ArrayList<>();
    }

    public void addWeightLog(WeightLog log) {
        this.weightLogs.add(log);
    }

    public void addExerciseLog(ExerciseLog log) {
        this.exerciseLogs.add(log);
    }

    public void addNutritionLog(NutritionLog log) {
        this.nutritionLogs.add(log);
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public List<WeightLog> getWeightLogs() {
        return weightLogs;
    }

    public List<ExerciseLog> getExerciseLogs() {
        return exerciseLogs;
    }

    public List<NutritionLog> getNutritionLogs() {
        return nutritionLogs;
    }
}