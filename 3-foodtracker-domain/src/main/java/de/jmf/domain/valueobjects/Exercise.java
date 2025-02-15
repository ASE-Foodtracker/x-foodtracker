// VO: Represents a specific type of exercise (e.g., "Push-ups").  Should be immutable.

package de.jmf.domain.valueobjects;

public class Exercise {
    private String name;
    private int duration; // in minutes
    private String desc;

    public Exercise(String name, int duration, String desc) {
        this.name = name;
        this.duration = duration;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }
}
