package de.jmf.application.usecases.progress.Weight;

import java.util.ArrayList;
import java.util.List;

import de.jmf.application.repositories.ProgressRepository;
import de.jmf.domain.entities.WeightLog;
import de.jmf.domain.valueobjects.ProgressTracker;

public class SaveWeight {
    private final ProgressRepository progressRepository;

    public SaveWeight(ProgressRepository progressRepository) {
        this.progressRepository = progressRepository;
    }

    public List<String[]> execute() {
        ProgressTracker progressTracker = progressRepository.getProgress();
        List<String[]> weightLogs = new ArrayList<>();
        for (WeightLog weightLog : progressTracker.getWeightLogs()) {
            String[] line = new String[2];
            line[0] = weightLog.getDate().toString();
            line[1] = String.valueOf(weightLog.getWeight().getValue());
            weightLogs.add(line);
        }
        return weightLogs;
    }
}
