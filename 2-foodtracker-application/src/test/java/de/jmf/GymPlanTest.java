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
        GymPlanRepository gymPlanRepository = new GymPlanRepository();
        CreateGymPlan createGymPlan = new CreateGymPlan(gymPlanRepository);

        List<String[]> exercises = new ArrayList<>();
        exercises.add(new String[]{"Push-ups", "Strength", "Beginner", "Upper Body", "3", "10", "0"});
        exercises.add(new String[]{"Squats", "Strength", "Beginner", "Lower Body", "3", "10", "0"});
        exercises.add(new String[]{"Running", "Cardio", "Intermediate", "Full Body", "0", "0", "30"});

        createGymPlan.createPlan("gain", exercises, "john.doe@example.com");

        List<String[]> gymPlan = createGymPlan.getGymPlan("john.doe@example.com");
        assertNotNull(gymPlan);
        assertFalse(gymPlan.isEmpty());
        assertEquals(9, gymPlan.size()); 
    }

    @Test
    public void testCreateGymPlanForLooseWeight() throws Exception {
        GymPlanRepository gymPlanRepository = new GymPlanRepository();
        CreateGymPlan createGymPlan = new CreateGymPlan(gymPlanRepository);

        List<String[]> exercises = new ArrayList<>();
        exercises.add(new String[]{"Push-ups", "Strength", "Beginner", "Upper Body", "3", "10", "0"});
        exercises.add(new String[]{"Squats", "Strength", "Beginner", "Lower Body", "3", "10", "0"});
        exercises.add(new String[]{"Running", "Cardio", "Intermediate", "Full Body", "0", "0", "30"});

        createGymPlan.createPlan("loose", exercises, "john.doe@example.com");

        List<String[]> gymPlan = createGymPlan.getGymPlan("john.doe@example.com");
        assertNotNull(gymPlan);
        assertFalse(gymPlan.isEmpty());
        assertEquals(7, gymPlan.size()); 
    }

    @Test
    public void testGetGymPlan() throws Exception {
        GymPlanRepository gymPlanRepository = new GymPlanRepository();
        CreateGymPlan createGymPlan = new CreateGymPlan(gymPlanRepository);

        List<String[]> exercises = new ArrayList<>();
        exercises.add(new String[]{"Push-ups", "Strength", "Beginner", "Upper Body", "3", "10", "0"});

        createGymPlan.createPlan("gain", exercises, "john.doe@example.com");

        List<String[]> gymPlan = createGymPlan.getGymPlan("john.doe@example.com");
        assertNotNull(gymPlan);
        assertEquals(9, gymPlan.size());
    }

    @Test
    public void testSetGymPlan() throws Exception {
        GymPlanRepository gymPlanRepository = new GymPlanRepository();
        CreateGymPlan createGymPlan = new CreateGymPlan(gymPlanRepository);

        List<String[]> gymPlan = new ArrayList<>();
        gymPlan.add(new String[]{"Monday", "Push-ups", "Strength", "Beginner", "Upper Body", "3", "10", "0"});

        createGymPlan.setGymPlan(gymPlan);

        List<String[]> retrievedGymPlan = createGymPlan.getGymPlan("john.doe@example.com");
        assertNotNull(retrievedGymPlan);
        assertEquals(1, retrievedGymPlan.size());
        assertEquals("Push-ups", retrievedGymPlan.get(0)[1]);
    }
}