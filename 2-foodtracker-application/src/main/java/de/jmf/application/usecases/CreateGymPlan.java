package de.jmf.application.usecases;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import de.jmf.application.repositories.GymPlanRepository;

public class CreateGymPlan {
    private final GymPlanRepository gymPlanRepository;
    private final String gymExercisesPath = "data/output/megaGymDataset.csv";

    public CreateGymPlan(GymPlanRepository gymPlanRepository) {
        this.gymPlanRepository = gymPlanRepository;
    }

    public List<String> createPlan(String fitnessGoal) throws IOException {
        List<String> exercises = readExercisesFromCsv(gymExercisesPath);
        List<String> gymPlan = new ArrayList<>();

        if ("gain weight".equalsIgnoreCase(fitnessGoal)) {
            gymPlan.addAll(getRandomExercises(exercises, "Monday", 3));
            gymPlan.addAll(getRandomExercises(exercises, "Wednesday", 3));
            gymPlan.addAll(getRandomExercises(exercises, "Friday", 3));
        } else if ("reduce weight".equalsIgnoreCase(fitnessGoal)) {
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

    private List<String> readExercisesFromCsv(String filePath) throws IOException {
        List<String> exercises = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                exercises.add(line);
            }
        }
        return exercises;
    }

    private List<String> getRandomExercises(List<String> exercises, String day, int count) {
        List<String> filteredExercises = exercises.stream()
                .filter(exercise -> !exercise.contains("Cardio"))
                .collect(Collectors.toList());
        return getRandomItems(filteredExercises, day, count);
    }

    private List<String> getCardioExercises(List<String> exercises, String day) {
        List<String> filteredExercises = exercises.stream()
                .filter(exercise -> exercise.contains("Cardio"))
                .collect(Collectors.toList());
        return getRandomItems(filteredExercises, day, 1);
    }

    private List<String> getRandomItems(List<String> items, String day, int count) {
        List<String> randomItems = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            int index = random.nextInt(items.size());
            randomItems.add(day + ": " + items.get(index));
        }
        return randomItems;
    }

    public List getGymPlan() {
        // return the list of gym plans
        return new ArrayList<>();
    }
}