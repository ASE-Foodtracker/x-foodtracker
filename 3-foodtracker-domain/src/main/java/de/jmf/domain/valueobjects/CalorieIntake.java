// VO: The amount of calories consumed. Doesn't have an identity on its own.

package de.jmf.domain.valueobjects;

public class CalorieIntake {
    private final int calories;

    public CalorieIntake(int calories) {
        if (calories < 0) {
            throw new IllegalArgumentException("Calories must be non-negative");
        }
        this.calories = calories;
    }

    public int getCalories() {
        return calories;
    }

    @Override
    public String toString() {
        return calories + " kcal";
    }
}
