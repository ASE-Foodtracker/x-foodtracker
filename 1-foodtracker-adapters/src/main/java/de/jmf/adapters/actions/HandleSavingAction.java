package de.jmf.adapters.actions;

import java.util.HashMap;
import java.util.Map;

import de.jmf.adapters.handlers.ProgressHandler;
import de.jmf.adapters.handlers.UserHandler;
import de.jmf.adapters.helper.InputReader;
import de.jmf.adapters.helper.LoopUntilSuccessful;
import de.jmf.adapters.helper.Strings;
import de.jmf.adapters.menus.PerformMenuOption;
import de.jmf.adapters.menus.PrintMenus;
import de.jmf.adapters.menus.SaveOption;

public class HandleSavingAction implements Action {
    private final UserHandler userHandler;
    private final ProgressHandler progressHandler;
    private final InputReader inputValidator;
    private final PrintMenus printMenus;
    private final PerformMenuOption performMenuOption;
    private final LoopUntilSuccessful loopUntilSuccessful;

    public HandleSavingAction(UserHandler userHandler, ProgressHandler progressHandler) {
        this.userHandler = userHandler;
        this.progressHandler = progressHandler;
        this.inputValidator = new InputReader();
        this.printMenus = new PrintMenus();
        this.performMenuOption = new PerformMenuOption();
        this.loopUntilSuccessful = new LoopUntilSuccessful();
    }

    @Override
    public void execute() {

        this.printMenus.printSavingMenu();

        Map<SaveOption, Runnable> saveOptions = new HashMap<>();

        saveOptions.put(SaveOption.BACK, () -> {});
        saveOptions.put(SaveOption.ALL,() ->  this.saveAll());
        saveOptions.put(SaveOption.USER, () -> this.loopUntilSuccessful.execute(() -> this.userHandler.saveUser()));
        saveOptions.put(SaveOption.WEIGHT, () -> this.loopUntilSuccessful.execute(() -> this.progressHandler.saveWeight()));
        saveOptions.put(SaveOption.MEALS, () ->  this.loopUntilSuccessful.execute(() -> this.progressHandler.saveMeals()));


        try {
            int option = this.inputValidator.getInt(Strings.ENTER_THE_NUMBER_OF_THE_ACTION);
            SaveOption selected = SaveOption.fromInt(option);
            this.performMenuOption.execute(saveOptions, selected);
        } catch (Exception e) {
            System.out.println(Strings.AN_ERROR_OCCURED + ": " + e.getMessage());
        }
    }

    public void saveAll(){
        this.userHandler.saveUser();
        this.progressHandler.saveWeight();
        this.progressHandler.saveMeals();
    }
}