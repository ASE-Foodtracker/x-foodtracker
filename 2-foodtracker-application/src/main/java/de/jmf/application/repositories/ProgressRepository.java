package de.jmf.application.repositories;

import java.time.LocalDate;
import java.util.List;

import de.jmf.domain.entities.WeightLog;
import de.jmf.domain.valueobjects.ProgressTracker;
import de.jmf.domain.valueobjects.Weight;

public class ProgressRepository {
    private final ProgressTracker progressTracker;

    public ProgressRepository() {
        this.progressTracker = new ProgressTracker();
    }

    public void loadWeight(List<WeightLog> weightLogs) {
        for (WeightLog log: weightLogs) {
            this.progressTracker.addWeightLog(log);
        }
    }

    public void addWeight(Weight weight) {
        this.progressTracker.addWeightLog(new WeightLog(LocalDate.now(), weight));
    }

    public ProgressTracker getProgress() {
        return this.progressTracker;
    }
}
