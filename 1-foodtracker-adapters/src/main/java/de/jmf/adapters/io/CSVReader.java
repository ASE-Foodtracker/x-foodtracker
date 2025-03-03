package de.jmf.adapters.io;

import de.jmf.application.ports.DataReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CSVReader implements DataReader {
    private final Path filePath;

    public CSVReader(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public String read() {
        return "";
    }

    @Override
    public List<String[]> readAll() {
        if (Files.exists(filePath)) {
            try {
                return Files.lines(filePath)
                        .map(line -> line.split(","))
                        .collect(Collectors.toList());
            } catch (IOException e) {
                System.out.println("An error occurred while reading the file: "+e);
            }
        } else {
            System.out.println("File doesn't exist");
        }
        return new ArrayList<>();
    }
}
