package de.jmf.adapters.old;

import de.jmf.domain.entities.DietPlan;
import de.jmf.domain.entities.WorkoutPlan;
import de.jmf.domain.valueobjects.Weight;

import java.util.List;

public class UserDTO {
    private String id;
    private String name;
    private String email;
    private Weight weight;
    private String goalType;
    private List<WorkoutPlan> workoutPlans;
    private List<DietPlan> dietPlans;

    public UserDTO(String id, String name, String email, Weight weight, String goalType,
                   List<WorkoutPlan> workoutPlan, List<DietPlan> dietPlan) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.weight = weight;
        this.goalType = goalType;

        this.workoutPlans = workoutPlan;
        this.dietPlans = dietPlan;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Weight getWeight() {
        return weight;
    }

    public String getGoalType() {
        return goalType;
    }

    public List<WorkoutPlan> getWorkoutPlans() {
        return workoutPlans;
    }

    public List<DietPlan> getDietPlans() {
        return dietPlans;
    }
}