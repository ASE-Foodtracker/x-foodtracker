package de.jmf.adapters.handlers;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import de.jmf.adapters.helper.Strings;
import de.jmf.adapters.io.CSVReader;
import de.jmf.adapters.io.CSVWriter;
import de.jmf.application.usecases.progress.Meals.GetAllMeals;
import de.jmf.application.usecases.progress.Meals.GetTodaysMeals;
import de.jmf.application.usecases.progress.Meals.RemoveMeal;
import de.jmf.application.usecases.progress.Meals.SaveMeal;
import de.jmf.application.usecases.progress.Weight.LoadWeight;
import de.jmf.application.usecases.progress.Weight.SaveWeight;
import de.jmf.application.usecases.progress.Weight.TrackWeight;
import de.jmf.application.usecases.user.GetActiveUser;
import de.jmf.domain.decorator.CarbsDecorator;
import de.jmf.domain.decorator.FatDecorator;
import de.jmf.domain.entities.Meal;
import de.jmf.domain.entities.NutritionLog;
import de.jmf.domain.valueobjects.Weight;

public class ProgressHandler {
    private final Scanner scanner;
    private final TrackWeight trackWeight;
    private final SaveWeight saveWeight;
    private final LoadWeight loadWeight;
    private final GetActiveUser logUser;
    private final SaveMeal saveMeal;
    private final GetTodaysMeals logTodaysMeals;
    private final RemoveMeal removeMeal;
    private final GetAllMeals getAllMeals;

    public ProgressHandler(GetActiveUser logUser, TrackWeight trackWeight, SaveWeight saveWeight, LoadWeight loadWeight,
            SaveMeal saveMeal, GetTodaysMeals logTodaysMeals, RemoveMeal removeMeal, GetAllMeals getAllMeals) {
        scanner = new Scanner(System.in);
        this.trackWeight = trackWeight;
        this.saveWeight = saveWeight;
        this.logUser = logUser;
        this.loadWeight = loadWeight;

        this.saveMeal = saveMeal;
        this.logTodaysMeals = logTodaysMeals;
        this.removeMeal = removeMeal;
        this.getAllMeals = getAllMeals;
    }

    public void loadProgress() {
        String userName = logUser.execute().getEmail();

        Path outputDir = Paths.get("").resolve("data").resolve("output");
        Path weightPath = outputDir.resolve(userName).resolve("weight.csv");

        CSVReader weightReader = new CSVReader(weightPath);
        List<String[]> weightLog = weightReader.readAll();
        if (!weightLog.isEmpty()) {
            loadWeight.execute(weightLog);
        }

        Path mealPath = outputDir.resolve(userName).resolve("meals.csv");

        CSVReader mealReader = new CSVReader(mealPath);
        List<String[]> mealLog = mealReader.readAll();
        if (!mealLog.isEmpty()) {
            mealLog.remove(0);
            for (String[] log : mealLog) {
                this.saveMeal.execute(new Meal(log[1], Integer.parseInt(log[2]), Integer.parseInt(log[3])),
                        LocalDate.parse(log[0]));
            }
        }
    }

    public void newWeightEntry() {
        try {
            System.out.println();
            System.out.println("New Entry");
            Weight newWeight = new Weight(getDouble("Please enter your weight: "));
            double difference = trackWeight.execute(newWeight);
            Weight oldWeight = new Weight(newWeight.getValue() - difference);
            System.out.println("Successfully entered weight");
            System.out.println(oldWeight.toString() + " -> " + newWeight.toString() + " (" + difference + ")");
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public boolean saveWeight() {
        List<String[]> weightLog = saveWeight.execute();
        String[] head = new String[2];
        head[0] = "date";
        head[1] = "weight";
        weightLog.add(0, head);

        String email = logUser.execute().getEmail();

        Path outputPath = Paths.get("").resolve("data").resolve("output").resolve(email);
        CSVWriter csvWriter = new CSVWriter(outputPath.resolve("weight.csv"));
        csvWriter.createDirectory(outputPath);
        csvWriter.createFile("date,weight");
        csvWriter.clear();
        csvWriter.saveAll(weightLog);
        return true;
    }


    public boolean newMealEntry() {
        boolean successful = false;
        try {
            System.out.println();
            System.out.println("New Meal Entry");

            String name = getString("Please enter the name of the meal: ");
            int calories = getInt("Please enter the amount of calories: ");
            int protein = getInt("Please enter the amount of protein: ");
            int fat = getInt("Please enter the amount of fat: ");
            int carbs = getInt("Please enter the amount of carbs: ");

            Meal meal = new Meal(name, protein, calories);
            meal = new FatDecorator(meal, fat);
            meal = new CarbsDecorator(meal, carbs);

            saveMeal.execute(meal, LocalDate.now());

            System.out.println("Successfully entered meal");
            successful = true;
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        return successful;
    }

    public void logTodaysMeals() {
        List<NutritionLog> meals = logTodaysMeals.execute();
        if (meals.isEmpty()) {
            System.out.println("No meals logged today");
        } else {
            System.out.println("Logged meals:");
            for (NutritionLog log : meals) {
                Meal meal = log.getMeal();
                int index = meals.indexOf(log) + 1;
                System.out.println(index + ": " + meal.getName() + " - " + meal.getCalories() + "kcal - "
                        + meal.getProtein() + "g");
            }
        }
    }

    public boolean removeMeal() {
        boolean successful = false;
        try {
            System.out.println("Remove Meal");
            List<NutritionLog> meals = logTodaysMeals.execute();
            if (meals.isEmpty()) {
                System.out.println("No meals logged today");
            } else {
                System.out.println("Logged meals:");
                for (NutritionLog log : meals) {
                    Meal meal = log.getMeal();
                    int index = meals.indexOf(log) + 1;
                    System.out.println(index + ": " + meal.getName() + " - " + meal.getCalories() + "kcal - "
                            + meal.getProtein() + "g");
                }

                int index = getInt("Please enter the number of the meal you want to remove: ");
                NutritionLog meal = meals.get(index - 1);
                removeMeal.execute(meal);
                System.out.println("Successfully removed meal");
                successful = true;
            }
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        return successful;
    }

    public boolean saveMeals() {
        List<String[]> meals = getAllMeals.execute();
        String email = logUser.execute().getEmail();
        String[] head = new String[4];
        head[0] = "date";
        head[1] = "name";
        head[2] = "calories";
        head[3] = "protein";
        meals.add(0, head);

        Path outputPath = Paths.get("").resolve("data").resolve("output").resolve(email);
        CSVWriter csvWriter = new CSVWriter(outputPath.resolve("meals.csv"));
        csvWriter.createDirectory(outputPath);
        csvWriter.createFile("name,calories,protein");
        csvWriter.clear();

        csvWriter.saveAll(meals);
        return true;
    }


    private int getInt(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (Exception e) {
                System.out.println(Strings.THE_INPUT_WAS_NOT_VALID_TRY_AGAIN);
            }
        }
    }

    private String getString(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return scanner.nextLine().trim();
            } catch (Exception e) {
                System.out.println(Strings.THE_INPUT_WAS_NOT_VALID_TRY_AGAIN);
            }
        }
    }

    private double getDouble(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (Exception e) {
                System.out.println(Strings.THE_INPUT_WAS_NOT_VALID_TRY_AGAIN);
            }
        }
    }
}
