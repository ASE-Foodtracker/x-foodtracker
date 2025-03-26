package de.jmf.application.usecases.progress.Meals;

import java.time.LocalDate;

import de.jmf.application.repositories.ProgressRepository;
import de.jmf.domain.entities.Meal;

public class SaveMeal {
    private final ProgressRepository progressRepository;

    public SaveMeal(ProgressRepository progressRepository) {
        this.progressRepository = progressRepository;
    }

    public void execute(Meal meal, LocalDate date) {
        progressRepository.saveMeal(meal, date);
    }
}
