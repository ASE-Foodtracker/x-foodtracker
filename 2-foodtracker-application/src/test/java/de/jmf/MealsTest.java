package de.jmf;

import de.jmf.application.repositories.ProgressRepository;
import de.jmf.application.usecases.progress.Meals.*;
import de.jmf.domain.entities.Meal;
import de.jmf.domain.entities.NutritionLog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class MealsTest {

    private ProgressRepository progressRepository;
    private SaveMeal saveMeal;
    private GetTodaysMeals getTodaysMeals;
    private RemoveMeal removeMeal;
    private GetAllMeals getAllMeals;

    @BeforeEach
    public void setUp() {
        progressRepository = new ProgressRepository();
        saveMeal = new SaveMeal(progressRepository);
        getTodaysMeals = new GetTodaysMeals(progressRepository);
        removeMeal = new RemoveMeal(progressRepository);
        getAllMeals = new GetAllMeals(progressRepository);
    }

    @Test
    public void testSaveMeal() {
        Meal meal = new Meal("Chicken Breast", 30, 200);
        saveMeal.execute(meal, LocalDate.now());

        List<NutritionLog> meals = progressRepository.getNutritionLogs();
        assertNotNull(meals);
        assertEquals(1, meals.size());
        assertEquals("Chicken Breast", meals.get(0).getMeal().getName());
    }

    @Test
    public void testGetTodaysMeals() {
        Meal meal = new Meal("Chicken Breast", 30, 200);
        saveMeal.execute(meal, LocalDate.now());

        List<NutritionLog> meals = getTodaysMeals.execute();
        assertNotNull(meals);
        assertEquals(1, meals.size());
        assertEquals("Chicken Breast", meals.get(0).getMeal().getName());
    }

    @Test
    public void testRemoveMeal() {
        Meal meal = new Meal("Chicken Breast", 30, 200);
        saveMeal.execute(meal, LocalDate.now());

        List<NutritionLog> meals = getTodaysMeals.execute();
        assertEquals(1, meals.size());

        removeMeal.execute(meals.get(0));
        meals = getTodaysMeals.execute();
        assertTrue(meals.isEmpty());
    }

    @Test
    public void testGetAllMeals() {
        Meal meal1 = new Meal("Chicken Breast", 30, 200);
        Meal meal2 = new Meal("Salmon", 25, 300);
        saveMeal.execute(meal1, LocalDate.now());
        saveMeal.execute(meal2, LocalDate.now());

        List<String[]> allMeals = getAllMeals.execute();
        assertNotNull(allMeals);
        assertEquals(2, allMeals.size());
        assertEquals("Chicken Breast", allMeals.get(0)[1]);
        assertEquals("Salmon", allMeals.get(1)[1]);
    }
}