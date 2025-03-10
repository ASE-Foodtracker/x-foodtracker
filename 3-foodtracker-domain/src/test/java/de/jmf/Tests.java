package de.jmf;

import de.jmf.domain.entities.*;
import de.jmf.domain.valueobjects.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

public class Tests {

    @Test
    public void testUser() {
        FitnessGoal goal = new FitnessGoal("gain", new Weight(70));
        User user = new User("John Doe", 25, "john.doe@example.com", goal);

        assertEquals("John Doe", user.getName());
        assertEquals(25, user.getAge());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals(goal, user.getGoal());

        WorkoutPlan workoutPlan = new WorkoutPlan(1, "Plan A");
        user.addWorkoutPlan(workoutPlan);
        assertEquals(1, user.getWorkoutPlans().size());
    }

    @Test
    public void testMeal() {
        Meal meal = new Meal("Chicken Breast", 30, 200);

        assertEquals("Chicken Breast", meal.getName());
        assertEquals(30, meal.getProtein());
        assertEquals(200, meal.getCalories());
        assertEquals(200, meal.calculateTotalCalories());
    }

    @Test
    public void testWorkoutPlan() {
        WorkoutPlan workoutPlan = new WorkoutPlan(1, "Plan A");
        Exercise exercise = new Exercise("Push-ups", 10, "Push-ups description");

        workoutPlan.addExercise(exercise);
        assertEquals(1, workoutPlan.getExercises().size());
        assertEquals("Push-ups", workoutPlan.getExercises().get(0).getName());

        workoutPlan.removeExercise(exercise);
        assertEquals(0, workoutPlan.getExercises().size());
    }

    @Test
    public void testFitnessGoal() {
        Weight targetWeight = new Weight(70);
        FitnessGoal goal = new FitnessGoal("gain", targetWeight);

        assertEquals("gain", goal.getGoalType());
        assertEquals(70, goal.getTargetWeight());
    }

    @Test
    public void testExerciseLog() {
        Exercise exercise = new Exercise("Push-ups", 10, "Push-ups description");
        ExerciseLog exerciseLog = new ExerciseLog(1, LocalDate.now(), exercise);

        assertEquals(1, exerciseLog.getId());
        assertEquals(LocalDate.now(), exerciseLog.getDate());
        assertEquals(exercise, exerciseLog.getExercise());
    }
}