package de.jmf.adapters.old;

import de.jmf.application.old.PlanExporter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CSVPlanExporter implements PlanExporter { // More specific name, implementing the interface

    @Override
    public void exportToConsole(String formattedPlan) {
        System.out.println(formattedPlan);
    }

    @Override
    public void exportToFile(String formattedPlan, String filePath) {  // More generic method name
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(formattedPlan.replace("\\n", System.lineSeparator()));
            System.out.println("Plan successfully exported to: " + filePath); // Slightly improved message
        } catch (IOException e) {
            throw new PlanExportException("Failed to export plan to file: " + filePath, e);
        }
    }
}

//package de.jmf.adapters.exporter;
//
//import java.io.BufferedWriter;
//import java.io.FileWriter;
//import java.io.IOException;
//
// public class PlanExporter2 {
//
//    public void exportToConsole(String formattedPlan) {
//        System.out.println(formattedPlan);
//    }
//
//    public void exportToCSV(String formattedPlan, String filePath) {
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
//            writer.write(formattedPlan.replace("\\n", System.lineSeparator()));
//            System.out.println("Plan wurde erfolgreich nach: " + filePath + " exportiert.");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
