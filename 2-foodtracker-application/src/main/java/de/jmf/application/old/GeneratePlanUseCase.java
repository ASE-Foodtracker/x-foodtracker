//package de.jmf.application.old;
//
//import de.jmf.dto.UserDTO;
//import output.file_management.CSVReader;
//
//import java.util.List;
//import java.util.Random;
//import java.util.Scanner;
//
//public class GeneratePlanUseCase {
//    private final CSVUserRepository userRepository;
//    private final CSVReader csvReader;
//    private final String dietsFilePath = "data/diets.csv";
//    private final String gainFilePath = "data/gain.csv";
//    private final String gymDatasetPath = "data/megaGymDataset.csv";
//    Random random = new Random();
//    String reduceWeight = "abnehmen";
//    String weeklyFoodPlan = "Wöchentlicher Ernährungsplan";
//    String weeklyGymPlan = "Wöchentlicher Fitnessplan";
//
//
//    public GeneratePlanUseCase(CSVUserRepository userRepository, CSVReader csvReader) {
//        this.userRepository = userRepository;
//        this.csvReader = csvReader;
//    }
//
//    public UserDTO execute() {
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("Willkommen bei der FoodTracker App!");
//        System.out.print("Geben Sie Ihren Namen ein: ");
//        String name = scanner.nextLine();
//
//            System.out.println("Wie alt sind Sie?");
//            int alter = Integer.parseInt(scanner.nextLine());
//
//        System.out.print("Geben Sie Ihre E-Mail-Adresse ein: ");
//        String email = scanner.nextLine();
//
//        System.out.print("Geben Sie Ihr Gewicht ein (kg): ");
//        double weightValue = Double.parseDouble(scanner.nextLine());
//        Weight weight = new Weight(weightValue);
//
//        System.out.print("Möchten Sie abnehmen oder Muskeln aufbauen? (abnehmen/aufbauen): ");
//        String goalType = scanner.nextLine();
//
//        System.out.print("Was ist ihr Zielgewicht? ");
//        double weight2 = Double.parseDouble(scanner.nextLine());
//        Weight targetWeight = new Weight(weight2);
//
//        FitnessGoal ftGoal = new FitnessGoal(goalType, targetWeight, weight);
//
//        String userID = createUserId();
//        User user = new User(userID, name, alter, weight, email, ftGoal);
//
//        try {
//            createMealPlan(user);
//            createWorkoutPlan(user);
//        }catch(Exception e){
//            System.out.println("Etwas ist schief gelaufen. Versuche es nochmal!");
//            System.exit(500);
//        }
//
//        userRepository.save(user);
//
//        return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getWeight(), goalType, user.getWorkoutPlans(), user.getDietPlans());
//    }
//
//    private String createUserId(){
//        String number ="";
//        int randomLength = 6 + (int)(Math.random() * 5);
//        for(int i = 0; i<randomLength; i++){
//            int output = (int)(Math.random()*10);
//            number += Integer.toString(output);
//        }
//        return number;
//    }
//
//    private void createMealPlan(User user){
//        String path;
//        if(user.getGoal().getGoalType().equalsIgnoreCase(reduceWeight)) {
//            path = dietsFilePath;
//        }else {path = gainFilePath;}
//
//        DietPlan dietPlan = new DietPlan(1, weeklyFoodPlan);
//            List<String[]> dietMeals = csvReader.readCSV(path);
//            for (int i = 0; i < 7; i++) {
//                String[] meal = dietMeals.get(random.nextInt(dietMeals.size()));
//                dietPlan.addMeal(new Meal(
//                        meal[1],
//                        (int) Math.round(Double.parseDouble(meal[3])),
//                        (int) Math.round(Double.parseDouble(meal[6]))
//                ));
//            }
//            user.addDietPlan(dietPlan);
//    }
//
//    private void createWorkoutPlan(User user){
//        String[] trainingDays = {"Montag", "Mittwoch", "Freitag"}; //Can be adjusted for a more exhausting plan
//        int amountExercieses = 3; //For the strength exercises
//        int durationCardio = 60; //For beginners standard of 60min
//        String cardio = "Cardio";
//
//        WorkoutPlan workoutPlan = new WorkoutPlan(1, weeklyGymPlan);
//        List<String[]> exercises = csvReader.readCSV(gymDatasetPath);
//
//        if(user.getGoal().getGoalType().equalsIgnoreCase(reduceWeight)) {
//            //Every Day cardio
//            for(int i=0; i < 9; i++){
//                //just take cardio exercises
//                List<String[]> cardioExercises = exercises.stream()
//                        .filter(exercise -> exercise[2].equalsIgnoreCase(cardio))
//                        .toList();
//                String[] exercise = cardioExercises.get(random.nextInt(cardioExercises.size()));
//                workoutPlan.addExercise(new Exercise(exercise[0], durationCardio, exercise[1]));
//            }
//        }
//        else{
//            for (String day : trainingDays) {
//                int addedExercises = 0;
//                while (addedExercises < amountExercieses) {
//                    String[] exercise = exercises.get(random.nextInt(exercises.size()));
//                    //No cardio exercises
//                    if (!exercise[2].equalsIgnoreCase(cardio)) {
//                        workoutPlan.addExercise(new Exercise(exercise[0], 10, exercise[1]));
//                        addedExercises++;
//                    }
//                }
//            }
//        }
//        user.addWorkoutPlan(workoutPlan);
//    }
//}