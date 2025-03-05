package de.jmf.adapters.handlers;

import java.io.IOException;
import java.util.List;

import de.jmf.application.usecases.CreateGymPlan;
import de.jmf.adapters.io.ConsoleWriter;

public class GymPlanHandler {
    private final CreateGymPlan createGymPlan;
    private List<String> gymPlan;

    public GymPlanHandler(CreateGymPlan createGymPlan) {
        this.createGymPlan = createGymPlan;
    }

    public void createGymPlan(String fitnessGoal) {
        try {
            gymPlan = createGymPlan.createPlan(fitnessGoal);
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