package de.jmf.adapters.io;
// Use Case (Application Layer)

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CheckExistingCsvFiles {
    private static final String[] requiredFiles = {
        "data/input/diets.csv",
        "data/input/gain.csv",
        "data/input/nutrients.csv",
        "data/input/megaGymDataset.csv"
    };

    public List<String> getMissingOrEmptyFiles() {
        List<String> result = new ArrayList<>();
        for (String path : requiredFiles) {
            File file = new File(path);
            if (!file.exists() || file.length() == 0) {
                result.add(path);
            }
        }
        return result;
    }

    public void check() {
        List<String> missingOrEmpty = getMissingOrEmptyFiles();
        if (!missingOrEmpty.isEmpty()) {
            throw new IllegalStateException("Fehlende oder leere Dateien: " + missingOrEmpty);
        }
    }
}
