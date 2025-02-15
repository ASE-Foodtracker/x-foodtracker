//package de.jmf.application.repositories;
//
//import de.jmf.domain.entities.User;
//
//import java.io.FileWriter;
//import java.io.IOException;
//
//public class CSVUserRepository {
//
//    private final String filePath;
//
//    public CSVUserRepository(String filePath) {
//        this.filePath = filePath;
//    }
//
//    public void save(User user) {
//        try (FileWriter writer = new FileWriter(filePath, true)) {
//            writer.append(user.getId() + "," + user.getName() + "," + user.getAge() + ","
//                    + user.getWeight() + "," + user.getEmail() + "," + user.getGoal().getGoalType() + ", " + user.getGoal().getTargetWeight() + "\n");
//        } catch (IOException e) {
//            System.err.println("Fehler beim Speichern der Daten: " + e.getMessage());
//        }
//    }
//}