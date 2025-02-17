package de.jmf.adapters.io;

import de.jmf.application.ports.DataWriter;

import java.io.*;
import java.util.List;

public class CSVWriter implements DataWriter {
    private final String filePath;

    public CSVWriter(String filePath) {
        this.filePath = filePath;
    };

    @Override
    public void saveAll(List<String[]> input) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String[] line: input) {
                writer.write(line[0]+","+line[1]+","+line[2]+","+line[3]+","+line[4]+","+line[5]);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("An Error occurred during saving to a file: " + e.getMessage());
        }
    }

    @Override
    public void clear() {
        try {
            FileWriter fw = new FileWriter(this.filePath, false);
            PrintWriter pw = new PrintWriter(fw, false);
            pw.flush();
            pw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println("Something went wrong during clearing the file: "+e.getMessage());
        }
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
