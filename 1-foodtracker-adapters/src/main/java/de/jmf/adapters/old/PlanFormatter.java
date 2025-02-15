package de.jmf.adapters.old;

import de.jmf.domain.entities.DietPlan;
import de.jmf.domain.valueobjects.Exercise;
import de.jmf.domain.entities.Meal;
import de.jmf.domain.entities.WorkoutPlan;

public class PlanFormatter {

    public static String formatDietPlan(DietPlan plan) {
        StringBuilder sb = new StringBuilder();
        sb.append("Ernährungsplan: ").append(plan.getName()).append("\nGerichte:\n");

        String[] daysOfWeek = {"Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag", "Sonntag"};
        int dayIndex = 0;

        for (Meal meal : plan.getMeals()) {
            sb.append(daysOfWeek[dayIndex]).append(": ")
                    .append(meal.getName()).append(" - ")
                    .append(meal.getProtein()).append("g Protein, ")
                    .append(meal.getCalories()).append(" kcal\n");
            dayIndex = (dayIndex + 1) % 7;  // Nach Sonntag wieder Montag
        }
        return sb.toString();
    }

    public static String formatWorkoutPlan(WorkoutPlan plan) {
        StringBuilder sb = new StringBuilder();
        sb.append("Workoutplan: ").append(plan.getName()).append("\nÜbungen:\n");
        for(Exercise exercise : plan.getExercises()) {
            sb.append(exercise.getName()).append(" (")
                    .append(exercise.getDesc())
                    .append(" - ")
                    .append(exercise.getDuration())
                    .append(" min)\n");
        }

        return sb.toString();
    }
}
