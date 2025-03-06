package de.jmf.application.usecases.progress.Meals;

import de.jmf.application.repositories.ProgressRepository;
import de.jmf.domain.entities.NutritionLog;

public class RemoveMeal {
    private final ProgressRepository progressRepository;

    public RemoveMeal(ProgressRepository progressRepository) {
        this.progressRepository = progressRepository;
    }

    public void execute(NutritionLog meal) {
        // Remove Meal
        progressRepository.removeMeal(meal);
    }
}
