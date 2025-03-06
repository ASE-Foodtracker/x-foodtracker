package de.jmf.application.usecases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import de.jmf.application.repositories.GymPlanRepository;

public class CreateGymPlan {
    private final GymPlanRepository gymPlanRepository;
    private final String gainWeight = "gain";
    private final String looseWeight = "loose";

    public CreateGymPlan(GymPlanRepository gymPlanRepository) {
        this.gymPlanRepository = gymPlanRepository;
    }

    public void createPlan(String fitnessGoal, List<String[]> exercises, String userMail) throws IOException {
        List<String[]> gymPlan = new ArrayList<>();

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
        
        gymPlanRepository.setGymPlan(gymPlan);
    }

    private List<String[]> getRandomExercises(List<String[]> exercises, String day, int count) {
        List<String[]> filteredExercises = exercises.stream()
                .filter(exercise -> !exercise[1].contains("Cardio") && !exercise[2].contains("Cardio"))
                .collect(Collectors.toList());
        return getRandomItems(filteredExercises, day, count);
    }

    private List<String[]> getCardioExercises(List<String[]> exercises, String day) {
        List<String[]> filteredExercises = exercises.stream()
                .filter(exercise -> exercise[1].contains("Cardio") || exercise[2].contains("Cardio"))
                .collect(Collectors.toList());
        return getRandomItems(filteredExercises, day, 1);
    }

    private List<String[]> getRandomItems(List<String[]> items, String day, int count) {
        List<String[]> randomItems = new ArrayList<>();
        Random random = new Random();
        if (items.isEmpty()) {
            throw new IllegalArgumentException("The list of items must not be empty");
        }
        for (int i = 0; i < count; i++) {
            int index = random.nextInt(items.size());
            String[] exerciseWithDay = new String[items.get(index).length + 1];
            exerciseWithDay[0] = day;
            System.arraycopy(items.get(index), 0, exerciseWithDay, 1, items.get(index).length);
            randomItems.add(exerciseWithDay);
        }
        return randomItems;
    }

    public List<String[]> getGymPlan(String userMail) throws Exception {
        return gymPlanRepository.getGymPlan(userMail);
    }

    public void setGymPlan(List<String[]> gymPlan){
        gymPlanRepository.setGymPlan(gymPlan);
    }
}