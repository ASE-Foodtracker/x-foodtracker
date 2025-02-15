package de.jmf.domain.entities;

import de.jmf.domain.valueobjects.Exercise;

import java.util.ArrayList;
import java.util.List;

public class WorkoutPlan {
    private int id;
    private String name;
    private List<Exercise> exercises;

    public WorkoutPlan(int id, String name) {
        this.id = id;
        this.name = name;
        this.exercises = new ArrayList<>();
    }

    public void addExercise(Exercise exercise) {
        this.exercises.add(exercise);
    }

    public void removeExercise(Exercise exercise) {
        this.exercises.remove(exercise);
    }

    // Getters and Setters


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }
}