package de.jmf;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.jmf.application.repositories.ProgressRepository;
import de.jmf.application.usecases.progress.Meals.GetAllMeals;
import de.jmf.application.usecases.progress.Meals.GetTodaysMeals;
import de.jmf.application.usecases.progress.Meals.RemoveMeal;
import de.jmf.application.usecases.progress.Meals.SaveMeal;
import de.jmf.domain.decorator.CarbsDecorator;
import de.jmf.domain.decorator.FatDecorator;
import de.jmf.domain.entities.Meal;
import de.jmf.domain.entities.NutritionLog;

public class MealsTest {

    private ProgressRepository progressRepository;
    private SaveMeal saveMeal;
    private GetTodaysMeals getTodaysMeals;
    private RemoveMeal removeMeal;
    private GetAllMeals getAllMeals;

    @BeforeEach
    public void setUp() {
        progressRepository = mock(ProgressRepository.class);
        saveMeal = new SaveMeal(progressRepository);
        getTodaysMeals = new GetTodaysMeals(progressRepository);
        removeMeal = new RemoveMeal(progressRepository);
        getAllMeals = new GetAllMeals(progressRepository);
    }

    @Test
    public void testSaveMeal() {
        // Arrange
        Meal meal = new Meal("Chicken Breast", 30, 200);
        meal = new FatDecorator(meal, 10);
        meal = new CarbsDecorator(meal, 15);
        NutritionLog nutritionLog = new NutritionLog(LocalDate.now(), meal);

        // Act
        saveMeal.execute(meal, LocalDate.now());

        // Assert
        verify(progressRepository, times(1)).saveMeal(meal, LocalDate.now());
    }

    @Test
    public void testGetTodaysMeals() {
        // Arrange
        Meal meal = new Meal("Chicken Breast", 30, 200);
        meal = new FatDecorator(meal, 10);
        meal = new CarbsDecorator(meal, 15);
        NutritionLog nutritionLog = new NutritionLog(LocalDate.now(), meal);
        when(progressRepository.getNutritionLogs()).thenReturn(List.of(nutritionLog));

        // Act
        List<NutritionLog> meals = getTodaysMeals.execute();

        // Assert
        assertNotNull(meals, "Meals list should not be null");
        assertEquals(1, meals.size(), "There should be exactly one meal");
        assertEquals("Chicken Breast", meals.get(0).getMeal().getName(), "Meal name should match");
        assertEquals(200, meals.get(0).getMeal().getCalories(), "Calories should match");
        assertEquals(30, meals.get(0).getMeal().getProtein(), "Protein should match");
    }

    @Test
    public void testRemoveMeal() {
        // Arrange
        Meal meal = new Meal("Chicken Breast", 30, 200);
        NutritionLog nutritionLog = new NutritionLog(LocalDate.now(), meal);

        // Act
        removeMeal.execute(nutritionLog);

        // Assert
        verify(progressRepository, times(1)).removeMeal(nutritionLog);
    }

    @Test
    public void testGetAllMeals() {
        // Arrange
        Meal meal1 = new Meal("Chicken Breast", 30, 200);
        meal1 = new FatDecorator(meal1, 10);
        meal1 = new CarbsDecorator(meal1, 15);

        Meal meal2 = new Meal("Salmon", 25, 300);
        meal2 = new FatDecorator(meal2, 20);
        meal2 = new CarbsDecorator(meal2, 5);

        NutritionLog log1 = new NutritionLog(LocalDate.now(), meal1);
        NutritionLog log2 = new NutritionLog(LocalDate.now(), meal2);
        when(progressRepository.getNutritionLogs()).thenReturn(List.of(log1, log2));

        // Act
        List<String[]> allMeals = getAllMeals.execute();

        // Assert
        assertNotNull(allMeals, "All meals list should not be null");
        assertEquals(2, allMeals.size(), "There should be exactly two meals");
        assertArrayEquals(new String[] { LocalDate.now().toString(), "Chicken Breast", "200", "30" }, allMeals.get(0), "First meal data should match");
        assertArrayEquals(new String[] { LocalDate.now().toString(), "Salmon", "300", "25" }, allMeals.get(1), "Second meal data should match");
    }
}