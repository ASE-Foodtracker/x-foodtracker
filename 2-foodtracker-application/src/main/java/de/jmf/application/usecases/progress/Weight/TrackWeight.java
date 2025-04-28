package de.jmf.application.usecases.progress.Weight;

import java.util.List;

import de.jmf.application.repositories.ProgressRepository;
import de.jmf.domain.entities.WeightLog;
import de.jmf.domain.valueobjects.Weight;

public class TrackWeight {
    private final ProgressRepository progressRepository;

    public TrackWeight(ProgressRepository progressRepository) {
        this.progressRepository = progressRepository;
    }

    public double execute(Weight weight) {
        List<WeightLog> log = progressRepository.getProgress().getWeightLogs();
        if (!log.isEmpty()) {
            Weight previousWeight = log.get(log.size()-1).getWeight();
            progressRepository.addWeight(weight);
            return weight.getValue() - previousWeight.getValue();
        } else {
            progressRepository.addWeight(weight);
            return 0;
        }
    }
}
