package de.jmf.adapters.output;

import de.jmf.application.ports.DataWriter;

import java.io.BufferedWriter;
import java.io.IOException;

public class CSVWriter implements DataWriter {
    private String filePath;
    public CSVWriter(String filePath) {
        this.filePath = filePath;
    };

    @Override
    public boolean save() {
        return false;
    }

    @Override
    public boolean saveAll(String formattedPlan) {
        try (BufferedWriter writer = new BufferedWriter(new DataWriter(filePath))) {
            writer.write(formattedPlan.replace("\\n", System.lineSeparator()));
            System.out.println("Plan wurde erfolgreich nach: " + filePath + " exportiert.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean update() {
        return false;
    }

    @Override
    public boolean delete() {
        return false;
    }
}
