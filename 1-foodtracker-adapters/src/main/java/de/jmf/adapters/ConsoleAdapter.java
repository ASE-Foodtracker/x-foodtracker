package de.jmf.adapters;

import de.jmf.adapters.handlers.UserHandler;
import de.jmf.adapters.handlers.ProgressHandler;
import de.jmf.adapters.io.CSVReader;
import de.jmf.adapters.io.CSVWriter;
import de.jmf.application.usecases.progress.LoadWeight;
import de.jmf.application.usecases.progress.SaveWeight;
import de.jmf.application.usecases.progress.TrackWeight;
import de.jmf.application.usecases.user.CreateUser;
import de.jmf.application.usecases.user.LogUser;
import de.jmf.application.usecases.user.RegisterUser;
import de.jmf.application.usecases.user.SaveUser;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class ConsoleAdapter {
    private final Scanner scanner;
    private final UserHandler userHandler;
    private final ProgressHandler progressHandler;

    public ConsoleAdapter(CreateUser createUser, RegisterUser login, LogUser logUser, SaveUser saveUser,
                          TrackWeight trackWeight, SaveWeight saveWeight, LoadWeight loadWeight) {
        this.scanner = new Scanner(System.in);
        this.userHandler = new UserHandler(createUser, login, logUser, saveUser);
        this.progressHandler = new ProgressHandler(logUser, trackWeight, saveWeight, loadWeight);
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
                        this.progressHandler.newWeightEntry();
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

    private void init() {
        // purpose: check if all needed files exist and get the content for the user
        startup();
        progressHandler.loadProgress();
    }

    private void startup() {
        Path usersPath = Paths.get("").resolve("data").resolve("output").resolve("users.csv");
        CSVWriter usersWriter = new CSVWriter(usersPath);
        usersWriter.createFile("name,age,mail,goalType,targetWeight");

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

    private void printMenu() {
        System.out.println();
        System.out.println("Main Menu");
        System.out.println("1 - user details");
        System.out.println("2 - track your weight");
        System.out.println("0 - exit");
    }

    private void save() {
        this.userHandler.saveUser();
        this.progressHandler.saveWeight();
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
