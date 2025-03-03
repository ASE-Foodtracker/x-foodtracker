package de.jmf.application.usecases.progress;

import de.jmf.application.repositories.ProgressRepository;
import de.jmf.domain.entities.WeightLog;
import de.jmf.domain.valueobjects.Weight;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LoadWeight {
    private final ProgressRepository progressRepository;

    public LoadWeight(ProgressRepository progressRepository) {
        this.progressRepository = progressRepository;
    }

    public void execute(List<String[]> weightLog) {
        if (weightLog == null || weightLog.isEmpty()) {
            throw new IllegalArgumentException("Weight log cannot be null or empty");
        }

        List<WeightLog> log = weightLog.stream()
                .skip(1) // Skip the headline
                .map(this::parseWeightLogEntry)
                .collect(Collectors.toList());

        progressRepository.loadWeight(log);
    }

    private WeightLog parseWeightLogEntry(String[] line) {
        if (line.length < 2) {
            throw new IllegalArgumentException("Invalid weight log entry format");
        }

        LocalDate date = LocalDate.parse(line[0]);
        double weight = Double.parseDouble(line[1]);
        return new WeightLog(date, new Weight(weight));
    }
}
