package de.jmf.adapters.actions;

import de.jmf.adapters.handlers.ProgressHandler;
import de.jmf.adapters.handlers.UserHandler;
import de.jmf.adapters.helper.InputReader;
import de.jmf.adapters.helper.Strings;

public class HandleQuitAction implements Action {
    private final InputReader inputValidator;
    private final HandleSavingAction handleSavingAction;

    public HandleQuitAction(UserHandler userHandler, ProgressHandler progressHandler) {
        this.inputValidator = new InputReader();
        this.handleSavingAction = new HandleSavingAction(userHandler, progressHandler);
    }

    @Override
    public void execute() {
        String saving = inputValidator.getString(Strings.DO_YOU_WANT_TO_SAVE_BEFORE_QUITTING);
        if (saving.equals(Strings.YES)) {
            this.handleSavingAction.saveAll();
        }
        System.out.println("See you ^^");
    }
}