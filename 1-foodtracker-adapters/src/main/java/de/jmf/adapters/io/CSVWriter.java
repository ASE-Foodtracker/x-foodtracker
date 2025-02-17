package de.jmf.adapters.io;

import de.jmf.application.ports.DataWriter;

import java.io.*;

public class CSVWriter implements DataWriter {
    private String filePath;
    private File file;

    public CSVWriter(String filePath) {
        this.filePath = filePath;
    };

    @Override
    public boolean save() {
        return false;
    }

    @Override
    public boolean saveAll(String input) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(input.replace("\\n", System.lineSeparator()));
            System.out.println("Plan wurde erfolgreich nach: " + filePath + " exportiert.");
        } catch (IOException e) {
            System.out.println("A Error occurred during saving the plan. " + e.getMessage());
        }
        return true;
    }

    @Override
    public boolean update() {
        return false;
    }

    @Override
    public boolean delete() {
        return false;
    }

    public void createFile(String input) {
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                if (file.createNewFile()) {
                    try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
                        bw.write(input);
                    } catch (IOException e) {
                        System.out.println("A Error occurred during creating the csv. " + e.getMessage());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("A Error occurred during initializing the user file. " + e.getMessage());
        }
    }
}
