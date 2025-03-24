// VO: Is a snapshot of progress at a point in time.

package de.jmf.domain.valueobjects;

import de.jmf.domain.entities.NutritionLog;
import de.jmf.domain.entities.WeightLog;

import java.util.ArrayList;
import java.util.List;

public class ProgressTracker {
    private String mail;
    private final List<WeightLog> weightLogs;
    private final List<NutritionLog> nutritionLogs;

    public ProgressTracker() {
        this.weightLogs = new ArrayList<>();
        this.nutritionLogs = new ArrayList<>();
    }

    public void addWeightLog(WeightLog log) {
        this.weightLogs.add(log);
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

    public List<NutritionLog> getNutritionLogs() {
        return nutritionLogs;
    }
}
