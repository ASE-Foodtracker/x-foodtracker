package de.jmf.adapters.io;

import java.util.List;

public class ConsoleWriter {
    //to be continued

    public ConsoleWriter(){

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

        String[] firstEntry = gymPlan.get(0);
        if (firstEntry.length > 2 && firstEntry[2].contains("Sets")) {
            // Weight gain plan
            System.out.println("Day      | Exercise     | Sets | Reps");
            for (String[] entry : gymPlan) {
                System.out.println(String.join(" | ", entry));
            }
        } else {
            // Cardio plan
            System.out.println("Day      | Exercise | Duration");
            for (String[] entry : gymPlan) {
                System.out.println(String.join(" | ", entry));
            }
        }
    }
}
