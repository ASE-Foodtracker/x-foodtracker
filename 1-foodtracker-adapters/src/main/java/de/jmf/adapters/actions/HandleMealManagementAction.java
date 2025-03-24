package de.jmf.adapters.actions;

import java.util.HashMap;
import java.util.Map;

import de.jmf.adapters.handlers.ProgressHandler;
import de.jmf.adapters.helper.InputReader;
import de.jmf.adapters.helper.LoopUntilSuccessful;
import de.jmf.adapters.helper.Strings;
import de.jmf.adapters.menus.MealMenuOption;
import de.jmf.adapters.menus.PerformMenuOption;
import de.jmf.adapters.menus.PrintMenus;

public class HandleMealManagementAction implements Action {
    private final ProgressHandler progressHandler;
    private final InputReader inputValidator;
    private final PrintMenus printMenus;
    private final LoopUntilSuccessful loopUntilSuccessful;
    private final PerformMenuOption performMenuOption;

    public HandleMealManagementAction(ProgressHandler progressHandler) {
        this.progressHandler = progressHandler;
        this.inputValidator = new InputReader();
        this.printMenus = new PrintMenus();
        this.loopUntilSuccessful = new LoopUntilSuccessful();
        this.performMenuOption = new PerformMenuOption();
    }

    @Override
    public void execute() {

        this.printMenus.printMealManagementMenu();
        Map<MealMenuOption, Runnable> mealOptions = new HashMap<>();

        mealOptions.put(MealMenuOption.BACK, () -> {
        });
        mealOptions.put(MealMenuOption.ADD_MEAL, () -> this.loopUntilSuccessful.execute(() -> this.progressHandler.newMealEntry()));
        mealOptions.put(MealMenuOption.REMOVE_MEAL, () -> this.loopUntilSuccessful.execute(() -> progressHandler.removeMeal()));
        mealOptions.put(MealMenuOption.SHOW_MEALS, () -> this.progressHandler.logTodaysMeals());

        try {
            int option = this.inputValidator.getInt(Strings.PLEASE_ENTER_YOUR_CHOICE);
            MealMenuOption selected = MealMenuOption.fromInt(option);
            this.performMenuOption.execute(mealOptions, selected);
        } catch (Exception e) {
            System.out.println(Strings.AN_ERROR_OCCURED + ": " + e.getMessage());
        }
    }
}