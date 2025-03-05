package de.jmf.adapters.handlers;

import de.jmf.adapters.io.CSVReader;
import de.jmf.adapters.io.CSVWriter;
import de.jmf.application.usecases.progress.LoadWeight;
import de.jmf.application.usecases.progress.SaveWeight;
import de.jmf.application.usecases.progress.TrackWeight;
import de.jmf.application.usecases.user.LogUser;
import de.jmf.domain.valueobjects.Weight;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class ProgressHandler {
    private final Scanner scanner;
    private final TrackWeight trackWeight;
    private final SaveWeight saveWeight;
    private final LoadWeight loadWeight;
    private final LogUser logUser;

    public ProgressHandler(LogUser logUser, TrackWeight trackWeight, SaveWeight saveWeight, LoadWeight loadWeight) {
        scanner = new Scanner(System.in);
        this.trackWeight = trackWeight;
        this.saveWeight = saveWeight;
        this.logUser = logUser;
        this.loadWeight = loadWeight;
    }

    public void loadProgress() {
        String userName = logUser.execute().getEmail();

        Path outputDir = Paths.get("").resolve("data").resolve("output");
        Path weightPath = outputDir.resolve(userName).resolve("weight.csv");

        CSVReader weightReader = new CSVReader(weightPath);
        List<String[]> weightLog = weightReader.readAll();
        if (!weightLog.isEmpty()) {
            loadWeight.execute(weightLog);
        }
    }

    public void newWeightEntry() {
        try {
            System.out.println();
            System.out.println("New Entry");
            Weight newWeight = new Weight(getDouble("Please enter your weight: "));
            double difference = trackWeight.execute(newWeight);
            Weight oldWeight = new Weight(newWeight.getValue() - difference);
            System.out.println("Successfully entered weight");
            System.out.println(oldWeight.toString() + " -> " + newWeight.toString() + " ("+difference+")");
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public void saveWeight() {
        List<String[]> weightLog = saveWeight.execute();
        String[] head = new String[2];
        head[0] = "date";
        head[1] = "weight";
        weightLog.add(0, head);

        String email = logUser.execute().getEmail();

        // Clear data/output/users
        // Write Users to data/output/users
        Path outputPath = Paths.get("").resolve("data").resolve("output").resolve(email);
        CSVWriter csvWriter = new CSVWriter(outputPath.resolve("weight.csv"));
        csvWriter.createDirectory(outputPath);
        csvWriter.createFile("date,weight");
        csvWriter.clear();
        csvWriter.saveAll(weightLog);
    }

    private int getInt(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Integer.parseInt(scanner.next().trim());
            } catch (Exception e) {
                System.out.println("Wrong input please try again.");
            }
        }
    }

    private String getString(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return scanner.next().trim();
            } catch (Exception e) {
                System.out.println("Wrong input please try again.");
            }
        }
    }

    private double getDouble(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Double.parseDouble(scanner.next().trim());
            } catch (Exception e) {
                System.out.println("Wrong input please try again.");
            }
        }
    }
}
