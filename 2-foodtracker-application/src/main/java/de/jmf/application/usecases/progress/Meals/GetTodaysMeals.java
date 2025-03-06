package de.jmf.application.usecases.progress.Meals;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import de.jmf.application.repositories.ProgressRepository;
import de.jmf.domain.entities.NutritionLog;

public class GetTodaysMeals {
    private final ProgressRepository progressRepository;

    public GetTodaysMeals(ProgressRepository progressRepository) {
        this.progressRepository = progressRepository;
    }

    public List<NutritionLog> execute() {
        LocalDate date = LocalDate.now();
        List<NutritionLog> nutritionLogs = progressRepository.getNutritionLogs();

        return nutritionLogs.stream()
                .filter(log -> log.getDate().equals(date))
                .collect(Collectors.toList());
    }
}
