package de.jmf.adapters;

import de.jmf.adapters.io.CSVReader;
import de.jmf.adapters.io.CSVWriter;
import de.jmf.application.usecases.CreateUser;
import de.jmf.application.usecases.RegisterUser;
import de.jmf.domain.valueobjects.FitnessGoal;
import de.jmf.domain.valueobjects.Weight;
import de.jmf.application.exceptions.duplicateException;

import java.nio.file.Paths;
import java.util.Scanner;

public class ConsoleAdapter {
    private final Scanner scanner;
    private final CreateUser createUser;
    private final RegisterUser login;

    public ConsoleAdapter(CreateUser createUser, RegisterUser login) {
        this.scanner = new Scanner(System.in);
        this.createUser = createUser;
        this.login = login;
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
                        System.out.println("See you ^^");
                        running = false;
                        break;
                    case 1:
                        createUser();
                        break;
                    default:
                        System.out.println("The number you entered was not a valid option");
                        break;
                }
            } catch (Exception e) {
                System.out.println("A Error occurred" + e.getMessage());
            }
        }
    }

    private void startup() {
        boolean running = true;
        System.out.println("Welcome to foodtracker");
        while (running) {
            try {
                System.out.println("Would you like to:");
                System.out.println("1 - Log into your account");
                System.out.println("2 - Create a new account");
                int option = getInt("Please enter the number of the action you want to perform: ");
                switch (option) {
                    case 1:
                        running = login();;
                        break;
                    case 2:
                        running = createUser();
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

    private boolean login() {
        try {
            System.out.println();
            System.out.println("Registration");
            String mail = getString("Please enter your email: ");
            // load users.csv
            String currentPath = Paths.get("").toAbsolutePath().toString() + "/data/output/users.csv";
            CSVReader reader = new CSVReader(currentPath);
            login.setup(reader.readAll());
            // user login
            login.execute();
            System.out.println("You successfully logged into your account");
            return false;
        } catch (Exception e) {
            System.out.print("Something went wrong. " + e.getMessage());
            return true;
        }
    }

    private boolean createUser() {
        try {
            System.out.println("Creating New Account");
            String mail = getString("Email: ");
            String name = getString("Name: ");
            int age = getInt("Age: ");
            Weight weightC = new Weight(getDouble("Please enter you current weight: "));
            String goalType = getString("Do you want to (gain|loose) weight: ");
            Weight weightF = new Weight(getDouble("Please enter you target weight: "));
            FitnessGoal goal = new FitnessGoal(goalType, weightF, weightC);
            // load user.csv
            String currentPath = Paths.get("").toAbsolutePath().toString() + "/data/output/users.csv";
            CSVReader reader = new CSVReader(currentPath);
            createUser.setup(reader.readAll());
            // create user
            createUser.execute(mail, name, age, weightC, goal);
            System.out.println("You successfully created a new account");
            return false;
        } catch (duplicateException e) {
            return true;
        } catch (Exception e) {
            System.out.print("Something went wrong. " + e.getMessage());
            return true;
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

    private void printMenu() {
        System.out.println();
        System.out.println("Welcome to your favorite fitness app");
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
