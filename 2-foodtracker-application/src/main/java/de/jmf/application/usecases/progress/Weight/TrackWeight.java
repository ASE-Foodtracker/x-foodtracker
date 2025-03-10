package de.jmf.application.usecases.progress.Weight;

import de.jmf.application.repositories.ProgressRepository;
import de.jmf.domain.entities.WeightLog;
import de.jmf.domain.valueobjects.Weight;

import java.util.List;

public class TrackWeight {
    private final ProgressRepository progressRepository;

    public TrackWeight(ProgressRepository progressRepository) {
        this.progressRepository = progressRepository;
    }

    public double execute(Weight weight) {
        // Get previous weight for difference
        List<WeightLog> log = progressRepository.getProgress().getWeightLogs();
        if (!log.isEmpty()) {
            Weight previousWeight = log.get(log.size()-1).getWeight();
            // Add Weight to Progress
            progressRepository.addWeight(weight);
            // Return difference
            return weight.getValue() - previousWeight.getValue();
        } else {
            progressRepository.addWeight(weight);
            return 0;
        }
    }
}
