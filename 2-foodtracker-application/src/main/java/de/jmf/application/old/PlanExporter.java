package de.jmf.application.old;

public interface PlanExporter {
    void exportToConsole(String formattedPlan);
    void exportToFile(String formattedPlan, String filePath); // More generic name
}