//package application;
//
//import application.use_cases.GeneratePlanUseCase;
//import domain.entities.DietPlan;
//import domain.entities.User;
//import domain.entities.WorkoutPlan;
//import infrastructure.repositories.CSVUserRepository;
//import infrastructure.file_management.CSVReader;
//import output.formatters.PlanFormatter;
//import output.exporters.PlanExporter;
//
//import java.util.Objects;
//
//public class Main {
//    public static void main(String[] args) {
//
//        CSVUserRepository userRepository = new CSVUserRepository("main/infrastructure/userData/userData.csv");
//        CSVReader csvReader = new CSVReader();
//        GeneratePlanUseCase useCase = new GeneratePlanUseCase(userRepository, csvReader);
//
//        var userDTO = useCase.execute();
//
//        DietPlan dietPlan = userDTO.getDietPlans().get(0);
//        WorkoutPlan workoutPlan = userDTO.getWorkoutPlans().get(0);
//
//        String formattedDietPlan = PlanFormatter.formatDietPlan(dietPlan);
//        String formattedWorkoutPlan = PlanFormatter.formatWorkoutPlan(workoutPlan);
//
//        PlanExporter exporter = new PlanExporter();
//        exporter.exportToConsole(formattedDietPlan);
//        exporter.exportToConsole(formattedWorkoutPlan);
//
//        exporter.exportToCSV(formattedDietPlan, "main/infrastructure/exportedPlans/dietPlans.csv");
//        exporter.exportToCSV(formattedWorkoutPlan, "main/infrastructure/exportedPlans/workoutPlans.csv");
//
//        System.out.println("Der wöchentliche Plan wurde erfolgreich generiert und exportiert!");
//    }
//}

package de.jmf;

import de.jmf.adapters.ConsoleAdapter;
import de.jmf.application.repositories.UserRepository;
import de.jmf.application.usecases.CreateUser;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        CreateUser createUser = new CreateUser(userRepository);
        ConsoleAdapter console = new ConsoleAdapter(createUser);
        // Start the application
        console.running();
    }
}