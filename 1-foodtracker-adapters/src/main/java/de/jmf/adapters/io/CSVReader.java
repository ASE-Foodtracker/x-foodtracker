package de.jmf.adapters.io;

import de.jmf.application.ports.DataReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader implements DataReader {
    private final String filePath;

    public CSVReader(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String read() {
        return "";
    }

    @Override
    public List<String[]> readAll() {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                data.add(values);
            }
        } catch (IOException e) {
            System.out.println("An error occurred during reading the plan. " + e.getMessage());
        }
        return data;
    }
}
