package de.jmf.domain.entities;

import de.jmf.domain.valueobjects.FitnessGoal;
import de.jmf.domain.valueobjects.Weight;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private int age;
    private Weight weight;
    private String email;
    private List<WorkoutPlan> workoutPlans;
    private List<DietPlan> dietPlans;
    private FitnessGoal goal;

    public User(String name, int age, Weight weight, String email, FitnessGoal goal) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.email = email;
        this.goal = goal;
        this.workoutPlans = new ArrayList<>();
        this.dietPlans = new ArrayList<>();
    }

    public void addWorkoutPlan(WorkoutPlan plan) {
        this.workoutPlans.add(plan);
    }

    public void addDietPlan(DietPlan plan) {
        this.dietPlans.add(plan);
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Weight getWeight() {
        return weight;
    }

    public void setWeight(Weight weight) {
        this.weight = weight;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<WorkoutPlan> getWorkoutPlans() {
        return workoutPlans;
    }

    public void setWorkoutPlans(List<WorkoutPlan> workoutPlans) {
        this.workoutPlans = workoutPlans;
    }

    public List<DietPlan> getDietPlans() {
        return dietPlans;
    }

    public void setDietPlans(List<DietPlan> dietPlans) {
        this.dietPlans = dietPlans;
    }

    public FitnessGoal getGoal() {
        return goal;
    }

    public void setGoal(FitnessGoal goal) {
        this.goal = goal;
    }
}