package de.jmf.adapters.actions;

import de.jmf.adapters.handlers.GymPlanHandler;
import de.jmf.adapters.handlers.UserHandler;
import de.jmf.adapters.helper.InputReader;
import de.jmf.adapters.helper.Strings;

public class HandleGymPlanAction implements Action {
    private final InputReader inputValidator;
    private final UserHandler userHandler;
    private final GymPlanHandler gymPlanHandler;


    public HandleGymPlanAction(UserHandler userHandler, GymPlanHandler gymPlanHandler) {
        this.inputValidator = new InputReader();
        this.userHandler = userHandler;
        this.gymPlanHandler = gymPlanHandler;
    }

    @Override
    public void execute() {

        String fitnessGoal = this.userHandler.getUserFitnessGoal();
        String userMail = this.userHandler.getUserMail();
        while (true) {
            System.out.println(Strings.LOAD_YOUR_EXISTING_PLAN_OR_CREATE_A_NEW_ONE);
            String loadOrCreate = inputValidator.getString(Strings.PLEASE_ENTER_YOUR_CHOICE);

            if (loadOrCreate.equalsIgnoreCase(Strings.LOAD)) {
                handleGymPlanLoadAndPrint(userMail);
                break;
            } else if (loadOrCreate.equalsIgnoreCase(Strings.CREATE)) {
                handleGymPlanCreate(fitnessGoal, userMail);
                break;
            } else {
                System.out.println(Strings.THE_INPUT_WAS_NOT_VALID_TRY_AGAIN);
            }
        }
    }

    public void handleGymPlanLoadAndPrint(String userMail) {
        this.gymPlanHandler.loadGymPlan(userMail);
        this.gymPlanHandler.printGymPlan(userMail);
    }

    public void handleGymPlanCreate(String fitnessGoal, String userMail) {
        this.gymPlanHandler.createGymPlan(fitnessGoal, userMail);
        System.out.println(Strings.DO_YOU_WANT_TO_SEE_THE_PLAN_FIRST_IN_THE_CONSOLE);
        String seePlan = inputValidator.getString(Strings.PLEASE_ENTER_YOUR_CHOICE);
        if (seePlan.equalsIgnoreCase(Strings.YES)) {
            System.out.println();
            this.gymPlanHandler.printGymPlan(userMail);
        }
        System.out.println(Strings.DO_YOU_WANT_TO_SAVE_THE_PLAN_RETRY_OR_EXIT);
        String savePlan = inputValidator.getString(Strings.PLEASE_ENTER_YOUR_CHOICE);
        if (savePlan.equalsIgnoreCase(Strings.SAVE)) {
            this.gymPlanHandler.saveGymPlan(userMail);
        } else if (savePlan.equalsIgnoreCase(Strings.EXIT)) {
        } else if (savePlan.equalsIgnoreCase(Strings.RETRY)) {
        } else {
            System.out.println(Strings.THE_INPUT_WAS_NOT_VALID_YOU_WILL_GET_NAVIGATED_BACK_TO_THE_MENU);
        }
    }
}