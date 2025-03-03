package de.jmf.adapters.handlers;

import de.jmf.adapters.io.CSVReader;
import de.jmf.adapters.io.CSVWriter;
import de.jmf.application.exceptions.duplicateException;
import de.jmf.application.usecases.user.CreateUser;
import de.jmf.application.usecases.user.LogUser;
import de.jmf.application.usecases.user.RegisterUser;
import de.jmf.application.usecases.user.SaveUser;
import de.jmf.domain.entities.User;
import de.jmf.domain.valueobjects.FitnessGoal;
import de.jmf.domain.valueobjects.Weight;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class UserHandler {

    private final CreateUser createUser;
    private final RegisterUser login;
    private final LogUser logUser;
    private final SaveUser saveUser;

    private final Scanner scanner;

    public UserHandler(CreateUser createUser, RegisterUser login, LogUser logUser, SaveUser saveUser) {
        this.createUser = createUser;
        this.login = login;
        this.logUser = logUser;
        this.saveUser = saveUser;
        this.scanner = new Scanner(System.in);
    }

    public void saveUser() {
        List<String[]> users = saveUser.execute();
        String[] head = new String[6];
        head[0] = "name";
        head[1] = "age";
        head[2] = "mail";
        head[3] = "goalType";
        head[4] = "targetWeight";

        users.add(0, head);
        // Clear data/output/users
        // Write Users to data/output/users
        Path outputPath = Paths.get("").resolve("data").resolve("output").resolve("users.csv");
        CSVWriter csvWriter = new CSVWriter(outputPath);
        csvWriter.clear();
        csvWriter.saveAll(users);
    }

    public User logUser() {
        try {
            System.out.println();
            System.out.println("Current Active User");
            User user = logUser.execute();
            System.out.println("Mail: " + user.getEmail());
            System.out.println("Name: " + user.getName());
            System.out.println("Age: " + user.getAge());
            System.out.println("Goal: " + user.getGoal().getGoalType());
            return user;
        } catch (Exception e) {
            System.out.println("User couldn't be fetched: " + e.getMessage());
        }
        return null;
    }

    public boolean login() {
        try {
            System.out.println();
            System.out.println("Registration");
            String mail = getString("Please enter your email: ");
            // load users.csv
            Path currentPath = Paths.get("").resolve("data").resolve("output").resolve("users.csv");
            login.setup(new CSVReader(currentPath).readAll());
            // user login
            login.execute(mail);
            System.out.println("You successfully logged into your account");
            return false;
        } catch (Exception e) {
            System.out.print("Something went wrong. " + e.getMessage());
            return true;
        }
    }

    public boolean createUser() {
        try {
            System.out.println("Creating New Account");
            String mail = getString("Email: ");
            String name = getString("Name: ");
            int age = getInt("Age: ");
            String goalType = getString("Do you want to (gain|loose) weight: ");
            Weight weightF = new Weight(getDouble("Please enter you target weight: "));
            FitnessGoal goal = new FitnessGoal(goalType, weightF);
            // load user.csv
            Path currentPath = Paths.get("").resolve("data").resolve("output").resolve("users.csv");
            createUser.setup(new CSVReader(currentPath).readAll());
            // create user
            createUser.execute(mail, name, age, goal);
            System.out.println("You successfully created a new account");
            return false;
        } catch (duplicateException e) {
            System.out.println(e.getMessage());
            return true;
        } catch (Exception e) {
            System.out.print("Something went wrong. " + e.getMessage());
            return true;
        }
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
