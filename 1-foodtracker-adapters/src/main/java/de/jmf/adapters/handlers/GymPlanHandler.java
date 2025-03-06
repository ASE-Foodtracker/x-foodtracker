package de.jmf.adapters.handlers;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import de.jmf.adapters.io.CSVReader;
import de.jmf.adapters.io.ConsoleWriter;
import de.jmf.application.usecases.CreateGymPlan;

public class GymPlanHandler {
    private final CreateGymPlan createGymPlan;
    private List<String> gymPlan;

    public GymPlanHandler(CreateGymPlan createGymPlan) {
        this.createGymPlan = createGymPlan;
    }

    public void createGymPlan(String fitnessGoal) {
        try {
            //hier den reader nehmen und in createplan schicken
            Path inputDir = Paths.get("").resolve("data").resolve("input").resolve("megaGymDataset.csv");
            CSVReader gymReader = new CSVReader(inputDir);

            this.gymPlan = createGymPlan.createPlan(fitnessGoal, gymReader.readAll());
            System.out.println("Gym plan created successfully.");
        } catch (IOException e) {
            System.out.println("Error creating gym plan: " + e.getMessage());
        }
    }

    public void saveGymPlan(String userMail){
        //to be continued
    }

    public List<String> getGymPlan() {
        if(gymPlan == null){
            System.out.println("Gym plan not created yet.");
            //try to get an existing one of the csv with the user mail
            return null;
        }else{
            System.out.println("Gym plan retrieved successfully.");
            return gymPlan;
        }
    }

    public void printGymPlan() {
        // throw new UnsupportedOperationException("Not supported yet.");
        ConsoleWriter consoleWriter = new ConsoleWriter();
        consoleWriter.printGymPlan(gymPlan);
    }
}