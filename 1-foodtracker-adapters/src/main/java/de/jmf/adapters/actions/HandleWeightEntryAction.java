package de.jmf.adapters.actions;

import de.jmf.adapters.handlers.ProgressHandler;

public class HandleWeightEntryAction implements Action {
    private final ProgressHandler progressHandler;

    public HandleWeightEntryAction(ProgressHandler progressHandler) {
        this.progressHandler = progressHandler;
    }
    @Override
    public void execute() {
        this.progressHandler.newWeightEntry();
    }
}