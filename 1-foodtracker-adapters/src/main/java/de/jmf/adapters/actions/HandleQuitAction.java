package de.jmf.adapters.actions;

import de.jmf.adapters.handlers.ProgressHandler;
import de.jmf.adapters.handlers.UserHandler;
import de.jmf.adapters.helper.InputValidator;
import de.jmf.adapters.helper.strings;

public class HandleQuitAction implements Action {
    private final InputValidator inputValidator;
    private final HandleSavingAction handleSavingAction;

    public HandleQuitAction(UserHandler userHandler, ProgressHandler progressHandler) {
        this.inputValidator = new InputValidator();
        this.handleSavingAction = new HandleSavingAction(userHandler, progressHandler);
    }

    @Override
    public void execute() {
        String saving = inputValidator.getString(strings.DO_YOU_WANT_TO_SAVE_BEFORE_QUITTING);
        if (saving.equals(strings.YES)) {
            this.handleSavingAction.saveAll();
        }
        System.out.println("See you ^^");
    }
}