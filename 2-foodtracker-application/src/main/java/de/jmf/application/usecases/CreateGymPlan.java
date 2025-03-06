package de.jmf.application.usecases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import de.jmf.application.repositories.GymPlanRepository;

public class CreateGymPlan {
    private final GymPlanRepository gymPlanRepository;
    private final String gymExercisesPath = "data/input/megaGymDataset.csv";
    private final String gainWeight = "gain";
    private final String looseWeight = "loose";

    public CreateGymPlan(GymPlanRepository gymPlanRepository) {
        this.gymPlanRepository = gymPlanRepository;
    }

    public List<String> createPlan(String fitnessGoal, List<String[]> exercises) throws IOException {
        List<String> gymPlan = new ArrayList<>();

        System.out.println(fitnessGoal);
        if (gainWeight.equalsIgnoreCase(fitnessGoal)) {
            gymPlan.addAll(getRandomExercises(exercises, "Monday", 3));
            gymPlan.addAll(getRandomExercises(exercises, "Wednesday", 3));
            gymPlan.addAll(getRandomExercises(exercises, "Friday", 3));
        } else if (looseWeight.equalsIgnoreCase(fitnessGoal)) {
            gymPlan.addAll(getCardioExercises(exercises, "Monday"));
            gymPlan.addAll(getCardioExercises(exercises, "Tuesday"));
            gymPlan.addAll(getCardioExercises(exercises, "Wednesday"));
            gymPlan.addAll(getCardioExercises(exercises, "Thursday"));
            gymPlan.addAll(getCardioExercises(exercises, "Friday"));
            gymPlan.addAll(getCardioExercises(exercises, "Saturday"));
            gymPlan.addAll(getCardioExercises(exercises, "Sunday"));
        }

        return gymPlan;
    }

   private List<String> getRandomExercises(List<String[]> exercises, String day, int count) {
        List<String[]> filteredExercises = exercises.stream()
                .filter(exercise -> !exercise[1].contains("Cardio"))
                .collect(Collectors.toList());
        return getRandomItems(filteredExercises, day, count);
    }

    private List<String> getCardioExercises(List<String[]> exercises, String day) {
        List<String[]> filteredExercises = exercises.stream()
                .filter(exercise -> exercise[1].contains("Cardio"))
                .collect(Collectors.toList());
        return getRandomItems(filteredExercises, day, 1);
    }

    private List<String> getRandomItems(List<String[]> items, String day, int count) {
        List<String> randomItems = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            int index = random.nextInt(items.size());
            randomItems.add(day + ": " + String.join(", ", items.get(index)));
        }
        return randomItems;
    }

    public List getGymPlan() {
        // return the list of gym plans
        return new ArrayList<>();
    }
}