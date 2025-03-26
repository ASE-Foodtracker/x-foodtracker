package de.jmf.domain.valueobjects;

public class FitnessGoal {
    private final String goalType;
    private final Weight targetWeight;

    public FitnessGoal(String goalType, Weight targetWeight) {
        this.goalType = goalType;
        this.targetWeight = targetWeight;
    }

    public String getGoalType() {
        return goalType;
    }

    public double getTargetWeight() {
        return targetWeight.getValue();
    }
}
