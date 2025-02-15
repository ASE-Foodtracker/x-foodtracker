package de.jmf.application.old;

import java.util.List;

public interface FileReader {
    List<String[]> readCSV(String filePath);
}