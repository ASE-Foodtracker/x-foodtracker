package de.jmf.domain.valueobjects;

public class FitnessGoal {
    private final String goalType;  // "gain" or "loose"
    private final Weight targetWeight;

    public FitnessGoal(String goalType, Weight targetWeight) {
        this.goalType = goalType;
        this.targetWeight = targetWeight;
    }

    // Getters and Setters
    public String getGoalType() {
        return goalType;
    }

    public double getTargetWeight() {
        return targetWeight.getValue();
    }
}
