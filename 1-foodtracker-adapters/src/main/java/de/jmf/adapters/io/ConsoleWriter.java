package de.jmf.adapters.io;

import java.util.List;

public class ConsoleWriter {
    private static final int DURATION = 60;
    private static final int SETS = 3;
    private static final int REPS = 10;

    public ConsoleWriter() {
    }

    public void printGymPlan(List<String[]> gymPlan) {
        if (gymPlan == null) {
            System.out.println("Gym plan not created yet.");
            return;
        }

        if (gymPlan.isEmpty()) {
            System.out.println("Gym plan is empty.");
            return;
        }

        System.out.printf("%-10s | %-60s | %-20s | %-20s | %-15s | %-10s | %-5s | %-5s", "Day", "Exercise", "Type", "Level", "BodyPart", "Sets", "Reps", "Duration");
        System.out.println("\n----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        for (String[] entry : gymPlan) {
            String day = entry[0].isEmpty()?"-":entry[0];
            String exercise = entry[1].isEmpty()?"-":entry[1];
            String type = entry[2].isEmpty()?"-":entry[2];
            String level = entry[3].isEmpty()?"-":entry[3];
            String bodyPart = entry[4].isEmpty()?"-":entry[4];

            if (type.equalsIgnoreCase("Cardio")) {
                System.out.printf("%-10s | %-60s | %-20s | %-20s | %-15s | %-10s | %-5s | %-5s%n", day, exercise, type, level, bodyPart, " - ", " - ", DURATION);
            } else {
                System.out.printf("%-10s | %-60s | %-20s | %-20s | %-15s | %-10s | %-5s | %-5s%n", day, exercise, type, level, bodyPart, SETS, REPS, " - ");
            }
        }
        System.out.println();
    }
}
