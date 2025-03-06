package de.jmf.adapters.io;

import de.jmf.application.ports.DataWriter;

import java.io.*;
import java.nio.file.Path;
import java.util.List;

public class CSVWriter implements DataWriter {
    private final Path filePath;

    public CSVWriter(Path filePath) {
        this.filePath = filePath;
    };

    @Override
    public void saveAll(List<String[]> input) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toString()))) {
            for (String[] line : input) {
                StringBuilder newLine = new StringBuilder();
                for (int i = 0; i < line.length; i++) {
                    newLine.append(line[i]);
                    if (i < line.length - 1) {
                        newLine.append(";");
                    }
                }
                writer.write(newLine.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("An Error occurred during saving to a file: " + e.getMessage());
        }
    }


    @Override
    public void clear() {
        try {
            FileWriter fw = new FileWriter(this.filePath.toString(), false);
            PrintWriter pw = new PrintWriter(fw, false);
            pw.flush();
            pw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println("Something went wrong during clearing the file: " + e.getMessage());
        }
    }

    public void createDirectory(Path path) {
        File file = new File(path.toString());
        if (!file.exists()) {
            file.mkdir();
        }
    }

    public void createFile(String input) {
        File file = new File(filePath.toString());
        try {
            if (!file.exists()) {
                if (file.createNewFile()) {
                    try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath.toString()))) {
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
