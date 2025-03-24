package de.jmf.domain.entities;

import java.util.ArrayList;
import java.util.List;

import de.jmf.domain.valueobjects.FitnessGoal;

public class User {
    private String name;
    private int age;
    private String email;
    private List<WorkoutPlan> workoutPlans;
    private FitnessGoal goal;

    private User(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.email = builder.email;
        this.goal = builder.goal;
        this.workoutPlans = builder.workoutPlans;
    }

    public static class Builder {
        private String name;
        private int age;
        private String email;
        private List<WorkoutPlan> workoutPlans = new ArrayList<>();
        private FitnessGoal goal;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setGoal(FitnessGoal goal) {
            this.goal = goal;
            return this;
        }

        public Builder setWorkoutPlans(List<WorkoutPlan> workoutPlans) {
            this.workoutPlans = workoutPlans;
            return this;
        }

        public User build() {
            return new User(this);
        }
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

    public FitnessGoal getGoal() {
        return goal;
    }

    public void setGoal(FitnessGoal goal) {
        this.goal = goal;
    }
}
