package de.jmf.adapters;

import de.jmf.application.usecases.CreateUser;
import de.jmf.domain.valueobjects.FitnessGoal;
import de.jmf.domain.valueobjects.Weight;

import java.util.Scanner;

public class ConsoleAdapter {
    private final Scanner scanner;
    private final CreateUser createUser;

    public ConsoleAdapter(CreateUser createUser) {
        this.scanner = new Scanner(System.in);
        this.createUser = createUser;
    }

    public void running() {
        boolean running = true;
        while(running) {
            try {
                printMenu();
                int option = getInt("Please enter number of the action you want to perform: ");
                switch (option) {
                    case 0:
                        System.out.println("See you ^^");
                        running = false;
                        break;
                    case 1:
                        createCustomer();
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

    private void createCustomer() {
        System.out.println("Create New User");
        String mail = getString("Email: ");
        String name = getString("Name: ");
        int age = getInt("Age: ");
        Weight weightC = new Weight(getDouble("Please enter you current weight: "));
        String goalType = getString("Do you want to (gain|loose) weight: ");
        Weight weightF = new Weight(getDouble("Please enter you target weight: "));
        FitnessGoal goal = new FitnessGoal(goalType, weightF, weightC);
        createUser.execute(mail,name,age,weightC,goal);
    }

    private void printMenu() {
        System.out.println("Welcome to your favorite fitness app");
        System.out.println("1 - Create Account");
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
