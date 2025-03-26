package de.jmf.adapters.handlers;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import de.jmf.adapters.io.CSVReader;
import de.jmf.adapters.io.CSVWriter;
import de.jmf.adapters.io.ConsoleWriter;
import de.jmf.application.usecases.CreateGymPlan;

public class GymPlanHandler {
    private final CreateGymPlan createGymPlan;

    public GymPlanHandler(CreateGymPlan createGymPlan) {
        this.createGymPlan = createGymPlan;
    }

    public void createGymPlan(String fitnessGoal, String userMail) {
        try {
            Path inputDir = Paths.get("").resolve("data").resolve("input").resolve("megaGymDataset.csv");
            CSVReader gymReader = new CSVReader(inputDir);

            this.createGymPlan.createPlan(fitnessGoal, gymReader.readAll(), userMail);
            System.out.println("Gym plan created successfully.");
        
        } catch (IOException e) {
            System.out.println("Error creating gym plan: " + e.getMessage());
        }
    }

    public void saveGymPlan(String userMail){
        List<String[]> gymPlan = this.createGymPlan.getGymPlan(userMail);
        
        if (gymPlan == null || gymPlan.isEmpty()) {
            System.out.println("No gym plan to save.");
            return;
        }
        gymPlan.add(0, new String[]{"Day", "Exercise", "Type", "Level", "BodyPart", "Sets", "Reps", "Duration"});

        Path outputPath = Paths.get("").resolve("data").resolve("output").resolve(userMail);
        CSVWriter csvWriter = new CSVWriter(outputPath.resolve("gymPlan.csv"));
        csvWriter.createDirectory(outputPath);

        csvWriter.clear();
        csvWriter.saveAll(gymPlan);

        System.out.println("Gym plan saved successfully.");
    }

    public void loadGymPlan(String userMail){
        Path inputDir = Paths.get("").resolve("data").resolve("output").resolve(userMail).resolve("gymPlan.csv");
        CSVReader gymReader = new CSVReader(inputDir);
        
        this.createGymPlan.setGymPlan(gymReader.readAll());
    }

    public void printGymPlan(String userMail){
        ConsoleWriter consoleWriter = new ConsoleWriter();
        List<String[]> gymPlan = this.createGymPlan.getGymPlan(userMail);
        if (gymPlan != null && !gymPlan.isEmpty()) {
            gymPlan.remove(0);
        }
        consoleWriter.printGymPlan(gymPlan);
    }
}