package de.jmf.application.usecases.progress.Meals;

import java.util.ArrayList;
import java.util.List;

import de.jmf.application.repositories.ProgressRepository;
import de.jmf.domain.entities.NutritionLog;

public class GetAllMeals {
    private final ProgressRepository progressRepository;

    public GetAllMeals(ProgressRepository progressRepository) {
        this.progressRepository = progressRepository;
    }

    public List<String[]> execute() {
        // Log all meals
        List<NutritionLog> log = progressRepository.getNutritionLogs();

        List<String[]> meals = new ArrayList<>();
        for (NutritionLog meal : log) {
            meals.add(new String[] { meal.getDate().toString(), meal.getMeal().getName(),
                    meal.getMeal().getCalories() + "",
                    meal.getMeal().getProtein() + "" });
        }

        return meals;
    }
}
