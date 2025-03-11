package de.jmf.adapters;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import de.jmf.adapters.handlers.GymPlanHandler;
import de.jmf.adapters.handlers.ProgressHandler;
import de.jmf.adapters.handlers.UserHandler;
import de.jmf.adapters.io.CSVWriter;
import de.jmf.application.usecases.CreateGymPlan;
import de.jmf.application.usecases.progress.Meals.GetAllMeals;
import de.jmf.application.usecases.progress.Meals.GetTodaysMeals;
import de.jmf.application.usecases.progress.Meals.RemoveMeal;
import de.jmf.application.usecases.progress.Meals.SaveMeal;
import de.jmf.application.usecases.progress.Weight.LoadWeight;
import de.jmf.application.usecases.progress.Weight.SaveWeight;
import de.jmf.application.usecases.progress.Weight.TrackWeight;
import de.jmf.application.usecases.user.CreateUser;
import de.jmf.application.usecases.user.GetActiveUser;
import de.jmf.application.usecases.user.LogOutUser;
import de.jmf.application.usecases.user.LoginUser;
import de.jmf.application.usecases.user.SaveUser;

public class ConsoleAdapter {
    private final Scanner scanner;
    private final UserHandler userHandler;
    private final GymPlanHandler gymPlanHandler; 
    private final ProgressHandler progressHandler;

    private static final String ENTER_THE_NUMBER_OF_THE_ACTION = "Please enter the number of the action you want to perform: ";
    private static final String DO_YOU_WANT_TO_SAVE_BEFORE_QUITTING = "Do you want so save before quitting? (yes|no): ";
    private static final String LOAD_YOUR_EXISTING_PLAN_OR_CREATE_A_NEW_ONE = "Do you want to load your existing plan or do you want to create a new one? (load/create)";
    private static final String PLEASE_ENTER_YOUR_CHOICE = "Please enter your choice: ";
    private static final String DO_YOU_WANT_TO_SEE_THE_PLAN_FIRST_IN_THE_CONSOLE = "Do you want to see the plan first in the console? (yes/no)";
    private static final String DO_YOU_WANT_TO_SAVE_THE_PLAN_RETRY_OR_EXIT = "Do you want to save the plan, retry or exit? (save/retry/exit)";

    private static final String THE_INPUT_WAS_NOT_VALID_YOU_WILL_GET_NAVIGATED_BACK_TO_THE_MENU = "The input was not valid. You will get navigated back to the menu";
    private static final String THE_INPUT_WAS_NOT_VALID_TRY_AGAIN = "The input was not valid. Please try again.";

    private static final String WELCOME_TO_YOUR_FAVORITE_FITNESS_APP = "Welcome to your favorite fitness app";
    private static final String WOULD_YOU_LIKE_TO = "Would you like to:";
    private static final String YES = "yes";
    private static final String NO = "no";
    private static final String SAVE = "save";
    private static final String EXIT_STRING = "exit";
    private static final String RETRY = "retry";
    private static final String CREATE = "create";
    private static final String LOAD = "load";

    public ConsoleAdapter(CreateUser createUser, LoginUser login, GetActiveUser logUser, SaveUser saveUser, CreateGymPlan createGymPlan,
            TrackWeight trackWeight, SaveWeight saveWeight, LoadWeight loadWeight, LogOutUser logOutUser,
            SaveMeal saveMeal, GetTodaysMeals logTodaysMeals, RemoveMeal removeMeal, GetAllMeals getAllMeals) {
        this.scanner = new Scanner(System.in);
        this.userHandler = new UserHandler(createUser, login, logUser, saveUser, logOutUser);
        this.gymPlanHandler = new GymPlanHandler(createGymPlan);
        this.progressHandler = new ProgressHandler(logUser, trackWeight, saveWeight, loadWeight, saveMeal,
                logTodaysMeals, removeMeal, getAllMeals);
    }

    private void handleQuit(){
        String saving = getString(DO_YOU_WANT_TO_SAVE_BEFORE_QUITTING);
        if (saving.equals(YES)) {
            save();
        }
        System.out.println("See you ^^");
    }

    private void handleGymPlan(){
        String fitnessGoal = this.userHandler.getUserFitnessGoal();
        String userMail = this.userHandler.getUserMail();
        while (true) {
            System.out.println(LOAD_YOUR_EXISTING_PLAN_OR_CREATE_A_NEW_ONE);
            String loadOrCreate = getString(PLEASE_ENTER_YOUR_CHOICE);

            if (loadOrCreate.equalsIgnoreCase(LOAD)) {
                // print the gymplan of the gymPlanRepository for the user
                handleGymPlanLoad(userMail);
                break;
            }else if(loadOrCreate.equalsIgnoreCase(CREATE)){
                handleGymPlanCreate(fitnessGoal, userMail);
                break;
            }else{
                System.out.println(THE_INPUT_WAS_NOT_VALID_TRY_AGAIN);
            }
        }
    }

    private void handleGymPlanLoad(String userMail){
        this.gymPlanHandler.loadGymPlan(userMail);
        this.gymPlanHandler.printGymPlan(userMail);
    }

    private void handleGymPlanCreate(String fitnessGoal, String userMail){
        this.gymPlanHandler.createGymPlan(fitnessGoal, userMail);
        System.out.println(DO_YOU_WANT_TO_SEE_THE_PLAN_FIRST_IN_THE_CONSOLE);
        System.out.println();
        String seePlan = getString(PLEASE_ENTER_YOUR_CHOICE);
        if (seePlan.equalsIgnoreCase(YES)) {
            System.out.println();
            this.gymPlanHandler.printGymPlan(userMail);
        }
        System.out.println(DO_YOU_WANT_TO_SAVE_THE_PLAN_RETRY_OR_EXIT);
        String savePlan = getString(PLEASE_ENTER_YOUR_CHOICE);
        if (savePlan.equalsIgnoreCase(SAVE)) {
            this.gymPlanHandler.saveGymPlan(userMail);
        }else if(savePlan.equalsIgnoreCase(EXIT_STRING)){
        }else if(savePlan.equalsIgnoreCase(RETRY)){
        }else{
            System.out.println(THE_INPUT_WAS_NOT_VALID_YOU_WILL_GET_NAVIGATED_BACK_TO_THE_MENU);
        }
    }

    private void handleUserLogin(){
        this.userHandler.logUser();
    }
    private void handleUserLogout(){
        this.userHandler.logOut();
        startup();
    }

    private void handleWeightEntry(){
        this.progressHandler.newWeightEntry();
    }

    public void running() {
        boolean running = true;
        init();
        while (running) {
            try {
                printMenu();
                int option = getInt(ENTER_THE_NUMBER_OF_THE_ACTION);
                switch (option) {
                    case 0:
                        handleQuit();
                        running = false;
                        break;
                    case 1:
                        handleUserLogin();
                        break;
                    case 2:
                        handleGymPlan();
                        break;
                    case 3:
                        handleWeightEntry();
                        break;
                    case 4:
                        handleMealManagement();
                        break;
                    case 5:
                        handleSaving();
                        break;
                    case 6:
                        handleUserLogout();
                        break;
                    default:
                        System.out.println(THE_INPUT_WAS_NOT_VALID_TRY_AGAIN);
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

    private void handleSaving() {
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

    private void handleMealManagement() {
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
