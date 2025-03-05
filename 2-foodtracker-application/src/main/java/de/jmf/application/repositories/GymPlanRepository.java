package de.jmf.application.repositories;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GymPlanRepository {
    private final Map<String, List<String>> gymPlans = new HashMap<>();

    public void saveGymPlan(String userMail, List<String> gymPlan) {
        String filePath = "gymPlans.csv";
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.append(userMail).append(",");
            for (String exercise : gymPlan) {
                writer.append(exercise).append(",");
            }
            writer.append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getGymPlan(String userId) {
        return gymPlans.get(userId);
    }
}