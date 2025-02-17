package de.jmf.adapters;

import de.jmf.adapters.handlers.UserHandler;
import de.jmf.adapters.io.CSVWriter;
import de.jmf.application.usecases.user.CreateUser;
import de.jmf.application.usecases.user.LogUser;
import de.jmf.application.usecases.user.RegisterUser;
import de.jmf.application.usecases.user.SaveUser;

import java.nio.file.Paths;
import java.util.Scanner;

public class ConsoleAdapter {
    private final Scanner scanner;
    private final UserHandler userHandler;

    public ConsoleAdapter(CreateUser createUser, RegisterUser login, LogUser logUser, SaveUser saveUser) {
        this.scanner = new Scanner(System.in);
        this.userHandler = new UserHandler(createUser, login, logUser, saveUser);
    }

    public void running() {
        boolean running = true;
        init();
        startup();
        while (running) {
            try {
                printMenu();
                int option = getInt("Please enter the number of the action you want to perform: ");
                switch (option) {
                    case 0:
                        this.userHandler.saveUser();
                        System.out.println("See you ^^");
                        running = false;
                        break;
                    case 1:
                        this.userHandler.logUser();
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
        // purpose: check if all needed files exist
        String dir = Paths.get("").toAbsolutePath().toString();
        String usersPath = dir + "/data/output/users.csv";
        String dietsPath = dir + "/data/input/diets.csv";
        String gainPath = dir + "/data/input/gain.csv";
        String gymPath = dir + "/data/input/megaFymDataset.csv";
        String nutrientsPath = dir + "/data/input/nutrients.csv";

        CSVWriter usersWriter = new CSVWriter(usersPath);
        usersWriter.createFile("name,age,currentWeight,mail,goalType,targetWeight");
    }

    private void startup() {
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
        System.out.println("0 - exit");
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
