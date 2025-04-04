package de.jmf;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

import de.jmf.application.repositories.GymPlanRepository;
import de.jmf.application.usecases.CreateGymPlan;

public class GymPlanTest {

    @Test
    public void testCreateGymPlanForGainWeight() throws Exception {
        // Arrange
        GymPlanRepository gymPlanRepository = new GymPlanRepository();
        CreateGymPlan createGymPlan = new CreateGymPlan(gymPlanRepository);

        List<String[]> exercises = new ArrayList<>();
        exercises.add(new String[]{"Push-ups", "Strength", "Beginner", "Upper Body", "3", "10", "0"});
        exercises.add(new String[]{"Squats", "Strength", "Beginner", "Lower Body", "3", "10", "0"});
        exercises.add(new String[]{"Running", "Cardio", "Intermediate", "Full Body", "0", "0", "30"});

        // Act
        createGymPlan.createPlan("gain", exercises, "john.doe@example.com");
        List<String[]> gymPlan = createGymPlan.getGymPlan("john.doe@example.com");

        // Assert
        assertNotNull(gymPlan, "Gym plan should not be null");
        assertFalse(gymPlan.isEmpty(), "Gym plan should not be empty");
        assertEquals(9, gymPlan.size(), "Gym plan should contain 9 exercises for 'gain' goal");
    }

    @Test
    public void testCreateGymPlanForLooseWeight() throws Exception {
        // Arrange
        GymPlanRepository gymPlanRepository = new GymPlanRepository();
        CreateGymPlan createGymPlan = new CreateGymPlan(gymPlanRepository);

        List<String[]> exercises = new ArrayList<>();
        exercises.add(new String[]{"Push-ups", "Strength", "Beginner", "Upper Body", "3", "10", "0"});
        exercises.add(new String[]{"Squats", "Strength", "Beginner", "Lower Body", "3", "10", "0"});
        exercises.add(new String[]{"Running", "Cardio", "Intermediate", "Full Body", "0", "0", "30"});

        // Act
        createGymPlan.createPlan("loose", exercises, "john.doe@example.com");
        List<String[]> gymPlan = createGymPlan.getGymPlan("john.doe@example.com");

        // Assert
        assertNotNull(gymPlan, "Gym plan should not be null");
        assertFalse(gymPlan.isEmpty(), "Gym plan should not be empty");
        assertEquals(7, gymPlan.size(), "Gym plan should contain 7 exercises for 'loose' goal");
    }

    @Test
    public void testGetGymPlan() throws Exception {
        // Arrange
        GymPlanRepository gymPlanRepository = new GymPlanRepository();
        CreateGymPlan createGymPlan = new CreateGymPlan(gymPlanRepository);

        List<String[]> exercises = new ArrayList<>();
        exercises.add(new String[]{"Push-ups", "Strength", "Beginner", "Upper Body", "3", "10", "0"});

        createGymPlan.createPlan("gain", exercises, "john.doe@example.com");

        // Act
        List<String[]> gymPlan = createGymPlan.getGymPlan("john.doe@example.com");

        // Assert
        assertNotNull(gymPlan, "Gym plan should not be null");
        assertEquals(9, gymPlan.size(), "Gym plan should contain 9 exercises for 'gain' goal");
    }

    @Test
    public void testSetGymPlan() throws Exception {
        // Arrange
        GymPlanRepository gymPlanRepository = new GymPlanRepository();
        CreateGymPlan createGymPlan = new CreateGymPlan(gymPlanRepository);

        List<String[]> gymPlan = new ArrayList<>();
        gymPlan.add(new String[]{"Monday", "Push-ups", "Strength", "Beginner", "Upper Body", "3", "10", "0"});

        // Act
        createGymPlan.setGymPlan(gymPlan);
        List<String[]> retrievedGymPlan = createGymPlan.getGymPlan("john.doe@example.com");

        // Assert
        assertNotNull(retrievedGymPlan, "Retrieved gym plan should not be null");
        assertEquals(1, retrievedGymPlan.size(), "Gym plan should contain exactly one exercise");
        assertEquals("Push-ups", retrievedGymPlan.get(0)[1], "Exercise name should match");
    }
}