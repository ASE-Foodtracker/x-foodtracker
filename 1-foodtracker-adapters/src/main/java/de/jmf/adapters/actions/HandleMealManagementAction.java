package de.jmf.adapters.actions;

import de.jmf.adapters.handlers.ProgressHandler;
import de.jmf.adapters.helper.InputValidator;

public class HandleMealManagementAction implements Action {
    private final ProgressHandler progressHandler;
    private final InputValidator inputValidator;

    public HandleMealManagementAction(ProgressHandler progressHandler) {
        this.progressHandler = progressHandler;
        this.inputValidator = new InputValidator();
    }

    @Override
    public void execute() {
        boolean running = true;
        while (running) {
            try {
                System.out.println();
                System.out.println("Meal Management");
                System.out.println("1 - add meal");
                System.out.println("2 - remove meal");
                System.out.println("3 - show todays meals");
                System.out.println("0 - back");
                int option = inputValidator.getInt("Please enter the number of the action you want to perform: ");
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
}