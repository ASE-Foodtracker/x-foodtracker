package de.jmf;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import de.jmf.domain.entities.ExerciseLog;
import de.jmf.domain.entities.Meal;
import de.jmf.domain.entities.User;
import de.jmf.domain.entities.WorkoutPlan;
import de.jmf.domain.valueobjects.Exercise;
import de.jmf.domain.valueobjects.FitnessGoal;
import de.jmf.domain.valueobjects.Weight;

public class Tests {

    @Test
    public void testUser() {
        // Arrange
        FitnessGoal goal = new FitnessGoal("gain", new Weight(70));
        User user = new User.Builder()
                .setName("John Doe")
                .setAge(25)
                .setEmail("john.doe@example.com")
                .setGoal(goal)
                .build();

        // Act & Assert
        assertEquals("John Doe", user.getName());
        assertEquals(25, user.getAge());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals(goal, user.getGoal());

        // Korrektur: Eine Liste von WorkoutPlan-Objekten erstellen
        WorkoutPlan workoutPlan = new WorkoutPlan(1, "Plan A");
        List<WorkoutPlan> workoutPlans = List.of(workoutPlan);
        user.setWorkoutPlans(workoutPlans);

        // Assert
        assertEquals(1, user.getWorkoutPlans().size());
        assertEquals("Plan A", user.getWorkoutPlans().get(0).getName());
    }

    @Test
    public void testMeal() {
        // Arrange
        Meal meal = new Meal("Chicken Breast", 30, 200);

        // Act & Assert
        assertEquals("Chicken Breast", meal.getName());
        assertEquals(30, meal.getProtein());
        assertEquals(200, meal.getCalories());
        assertEquals(200, meal.calculateTotalCalories());
    }

    @Test
    public void testWorkoutPlan() {
        // Arrange
        WorkoutPlan workoutPlan = new WorkoutPlan(1, "Plan A");
        Exercise exercise = new Exercise("Push-ups", 10, "Push-ups description");

        // Act
        workoutPlan.addExercise(exercise);

        // Assert
        assertEquals(1, workoutPlan.getExercises().size());
        assertEquals("Push-ups", workoutPlan.getExercises().get(0).getName());

        // Act
        workoutPlan.removeExercise(exercise);

        // Assert
        assertEquals(0, workoutPlan.getExercises().size());
    }

    @Test
    public void testFitnessGoal() {
        // Arrange
        Weight targetWeight = new Weight(70);
        FitnessGoal goal = new FitnessGoal("gain", targetWeight);

        // Act & Assert
        assertEquals("gain", goal.getGoalType());
        assertEquals(70, goal.getTargetWeight());
    }

    @Test
    public void testExerciseLog() {
        // Arrange
        Exercise exercise = new Exercise("Push-ups", 10, "Push-ups description");
        ExerciseLog exerciseLog = new ExerciseLog(1, LocalDate.now(), exercise);

        // Act & Assert
        assertEquals(1, exerciseLog.getId());
        assertEquals(LocalDate.now(), exerciseLog.getDate());
        assertEquals(exercise, exerciseLog.getExercise());
    }
}