package de.jmf.adapters;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import de.jmf.adapters.handlers.GymPlanHandler;
import de.jmf.adapters.handlers.ProgressHandler;
import de.jmf.adapters.handlers.UserHandler;
import de.jmf.adapters.io.CSVWriter;

import de.jmf.application.usecases.progress.Meals.*;
import de.jmf.application.usecases.progress.Weight.*;
import de.jmf.application.usecases.user.*;
import de.jmf.application.usecases.CreateGymPlan;

public class ConsoleAdapter {
    private final Scanner scanner;
    private final UserHandler userHandler;
    private final GymPlanHandler gymPlanHandler; 
    private final ProgressHandler progressHandler;

    public ConsoleAdapter(CreateUser createUser, LoginUser login, GetActiveUser logUser, SaveUser saveUser, CreateGymPlan createGymPlan,
            TrackWeight trackWeight, SaveWeight saveWeight, LoadWeight loadWeight, LogOutUser logOutUser,
            SaveMeal saveMeal, GetTodaysMeals logTodaysMeals, RemoveMeal removeMeal, GetAllMeals getAllMeals) {
        this.scanner = new Scanner(System.in);
        this.userHandler = new UserHandler(createUser, login, logUser, saveUser, logOutUser);
        this.gymPlanHandler = new GymPlanHandler(createGymPlan);
        this.progressHandler = new ProgressHandler(logUser, trackWeight, saveWeight, loadWeight, saveMeal,
                logTodaysMeals, removeMeal, getAllMeals);
    }

    public void running() {
        boolean running = true;
        init();
        while (running) {
            try {
                printMenu();
                int option = getInt("Please enter the number of the action you want to perform: ");
                switch (option) {
                    case 0:
                        String saving = getString("Do you want so save before quitting? (y|n): ");
                        if (saving.equals("y")) {
                            save();
                        }
                        System.out.println("See you ^^");
                        running = false;
                        break;
                    case 1:
                        this.userHandler.logUser();
                        break;
                    case 2:
                        String fitnessGoal = this.userHandler.getUserFitnessGoal();
                        String userMail = this.userHandler.getUserMail();
                        while (true) {
                            System.out.println("Do you want to load your existing plan or do you want to create a new one? (load/create)");
                            String loadOrCreate = getString("Please enter your choice: ");

                            if (loadOrCreate.equalsIgnoreCase("load")) {
                                // print the gymplan of the gymPlanRepository for the user
                                this.gymPlanHandler.loadGymPlan(userMail);
                                this.gymPlanHandler.printGymPlan(userMail);
                                break;
                            }else if(loadOrCreate.equalsIgnoreCase("create")){
                                this.gymPlanHandler.createGymPlan(fitnessGoal, userMail);
                                System.out.println("Do you want to see the plan first in the console? (yes/no)");
                                System.out.println();
                                String seePlan = getString("Please enter your choice: ");
                                if (seePlan.equalsIgnoreCase("yes")) {
                                    System.out.println();
                                    this.gymPlanHandler.printGymPlan(userMail);
                                }
                                System.out.println("Do you want to save the plan, retry or exit? (save/retry/exit)");
                                String savePlan = getString("Please enter your choice: ");
                                if (savePlan.equalsIgnoreCase("save")) {
                                    this.gymPlanHandler.saveGymPlan(userMail);
                                    break;
                                }else if(savePlan.equalsIgnoreCase("exit")){
                                    break;
                                }else if(savePlan.equalsIgnoreCase("retry")){
                                }else{
                                    System.out.println("The input was not valid. You will get navigated back to the menu");
                                    break;
                                }
                            }else{
                                System.out.println("The input was not valid. Please try again.");
                            }
                        }
                        break;
                    case 3:
                        this.progressHandler.newWeightEntry();
                        break;
                    case 4:
                        mealManagement();
                        break;
                    case 5:
                        saving();
                        break;
                    case 6:
                        this.userHandler.logOut();
                        startup();
                        break;
                    default:
                        System.out.println("The number you entered was not a valid option");
                        break;
                }
            } catch (Exception e) {
                System.out.println("An error occurred " + e.getMessage());
            }
        }
    }

    private void init() {
        // purpose: check if all needed files exist and get the content for the user
        startup();
        progressHandler.loadProgress();
    }

    private void startup() {
        Path usersPath = Paths.get("").resolve("data").resolve("output").resolve("users.csv");
        CSVWriter usersWriter = new CSVWriter(usersPath);
        usersWriter.createFile("name;age;mail;goalType;targetWeight");

        boolean running = true;
        System.out.println("Welcome to your favorite fitness app");
        while (running) {
            try {
                System.out.println("Would you like to:");
                System.out.println("1 - Log into your account");
                System.out.println("2 - Create a new account");
                int option = getInt("Please enter the number of the action you want to perform: ");
                switch (option) {
                    case 1:
                        running = this.userHandler.login();
                        break;
                    case 2:
                        running = this.userHandler.createUser();
                        break;
                    default:
                        System.out.println("The number you entered was not a valid option");
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void saving() {
        boolean repeat = true;
        System.out.println();
        System.out.println("Saving");
        System.out.println("1 - all");
        System.out.println("2 - user details");
        System.out.println("3 - weight progress");
        System.out.println("4 - meals");
        System.out.println("0 - back");
        while (repeat) {
            int option = getInt("Please enter the number of the file(s) you want to save: ");
            switch (option) {
                case 0:
                    repeat = false;
                    break;
                case 1:
                    save();
                    repeat = false;
                    break;
                case 2:
                    repeat = this.userHandler.saveUser();
                    break;
                case 3:
                    repeat = this.progressHandler.saveWeight();
                    break;
                case 4:
                    repeat = this.progressHandler.saveMeals();
                    break;
                default:
                    System.out.println("The number you entered was not a valid option");
                    break;
            }
        }
    }

    private void printMenu() {
        System.out.println();
        System.out.println("Main Menu");
        System.out.println("1 - user details");
        System.out.println("2 - Gymplan Menu");
        System.out.println("3 - track your weight");
        System.out.println("4 - meal management");
        System.out.println("5 - save");
        System.out.println("6 - log out");
        System.out.println("0 - exit");
    }

    private void mealManagement() {
        boolean running = true;
        while (running) {
            try {
                System.out.println();
                System.out.println("Meal Management");
                System.out.println("1 - add meal");
                System.out.println("2 - remove meal");
                System.out.println("3 - show todays meals");
                System.out.println("0 - back");
                int option = getInt("Please enter the number of the action you want to perform: ");
                switch (option) {
                    case 0:
                        running = false;
                        break;
                    case 1:
                        this.progressHandler.newMealEntry();
                        break;
                    case 2:
                        this.progressHandler.removeMeal();
                        break;
                    case 3:
                        this.progressHandler.logTodaysMeals();
                        break;
                    default:
                        System.out.println("The number you entered was not a valid option");
                        break;
                }
            } catch (Exception e) {
                System.out.println("An error occurred" + e.getMessage());
            }
        }
    }

    private void save() {
        this.userHandler.saveUser();
        this.progressHandler.saveWeight();
        this.progressHandler.saveMeals();
    }

    private int getInt(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Integer.parseInt(scanner.next().trim());
            } catch (Exception e) {
                System.out.println("Wrong input please try again.");
            }
        }
    }

    private String getString(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return scanner.next().trim();
            } catch (Exception e) {
                System.out.println("Wrong input please try again.");
            }
        }
    }

    private double getDouble(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Double.parseDouble(scanner.next().trim());
            } catch (Exception e) {
                System.out.println("Wrong input please try again.");
            }
        }
    }
}
