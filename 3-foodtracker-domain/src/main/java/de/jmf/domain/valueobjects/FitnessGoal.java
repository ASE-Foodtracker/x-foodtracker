package de.jmf.domain.valueobjects;

public class FitnessGoal {
    private final String goalType;  // "abnehmen" oder "muskelaufbau"
    private final Weight targetWeight;
    private final Weight currentWeight;

    public FitnessGoal(String goalType, Weight targetWeight, Weight currentWeight) {
        this.goalType = goalType;
        this.targetWeight = targetWeight;
        this.currentWeight = currentWeight;
    }

    public boolean isAchieved() {
        if (goalType.equalsIgnoreCase("loose")) {
            return currentWeight.getValue() <= targetWeight.getValue();
        } else if (goalType.equalsIgnoreCase("gain")) {
            return currentWeight.getValue() >= targetWeight.getValue();
        }
        return false;
    }

    public double getProgress() {
        return (currentWeight.getValue() / targetWeight.getValue()) * 100;
    }

    // Getters and Setters
    public String getGoalType() {
        return goalType;
    }

    public double getTargetWeight() {
        return targetWeight.getValue();
    }

    public double getCurrentWeight() {
        return currentWeight.getValue();
    }
}
