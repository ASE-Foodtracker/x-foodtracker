package de.jmf.domain.valueobjects;

import java.util.ArrayList;
import java.util.List;

import de.jmf.domain.entities.ExerciseLog;
import de.jmf.domain.entities.NutritionLog;
import de.jmf.domain.entities.WeightLog;

public class ProgressTracker {
    private String mail;
    private final List<WeightLog> weightLogs;
    private final List<ExerciseLog> exerciseLogs;
    private final List<NutritionLog> nutritionLogs;

    public ProgressTracker() {
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

    public void removeNutritionLog(NutritionLog log) {
        this.nutritionLogs.remove(log);
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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
