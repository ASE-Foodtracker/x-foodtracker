package de.jmf;

import de.jmf.application.repositories.ProgressRepository;
import de.jmf.application.usecases.progress.Weight.LoadWeight;
import de.jmf.application.usecases.progress.Weight.SaveWeight;
import de.jmf.application.usecases.progress.Weight.TrackWeight;
import de.jmf.domain.entities.WeightLog;
import de.jmf.domain.valueobjects.ProgressTracker;
import de.jmf.domain.valueobjects.Weight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class WeightTest {
    private ProgressRepository progressRepository;
    private TrackWeight trackWeight;
    private SaveWeight saveWeight;
    private LoadWeight loadWeight;

    @BeforeEach
    public void setUp() {
        progressRepository = mock(ProgressRepository.class);
        trackWeight = new TrackWeight(progressRepository);
        saveWeight = new SaveWeight(progressRepository);
        loadWeight = new LoadWeight(progressRepository);
    }

    @Test
    public void testTrackWeight() {
        Weight newWeight = new Weight(75);

        ProgressTracker progressTracker = new ProgressTracker();
        progressTracker.addWeightLog(new WeightLog(LocalDate.now().minusDays(1), new Weight(70.0)));
        when(progressRepository.getProgress()).thenReturn(progressTracker);

        double difference = trackWeight.execute(newWeight);
        assertEquals(5.0, difference);
        verify(progressRepository, times(1)).addWeight(newWeight);
    }

    @Test
    public void testSaveWeight() {
        ProgressTracker progressTracker = new ProgressTracker();
        progressTracker.addWeightLog(new WeightLog(LocalDate.now().minusDays(1), new Weight(70.0)));
        progressTracker.addWeightLog(new WeightLog(LocalDate.now(), new Weight(75.0)));
        when(progressRepository.getProgress()).thenReturn(progressTracker);

        List<String[]> savedWeights = saveWeight.execute();
        assertNotNull(savedWeights);
        assertEquals(2, savedWeights.size());
        assertArrayEquals(new String[] { LocalDate.now().toString(), "75.0" }, savedWeights.get(1));
    }

    @Test
    public void testLoadWeight() {
        List<String[]> weightLog = new ArrayList<>();
        weightLog.add(new String[] { "date", "weight" });
        weightLog.add(new String[] { LocalDate.now().toString(), "70.0" });

        loadWeight.execute(weightLog);
        verify(progressRepository, times(1)).loadWeight(anyList());
    }

    @Test
    public void testLoadWeightWithInvalidData() {
        List<String[]> weightLog = new ArrayList<>();
        weightLog.add(new String[] { "date", "weight" });
        weightLog.add(new String[] { LocalDate.now().toString() });

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            loadWeight.execute(weightLog);
        });
        assertEquals("Invalid weight log entry format", exception.getMessage());
    }
}
