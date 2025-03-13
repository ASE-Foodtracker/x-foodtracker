package de.jmf.adapters.actions;

import de.jmf.adapters.handlers.UserHandler;

public class HandleUserLoginAction implements Action {
    private final UserHandler userHandler;

    public HandleUserLoginAction(UserHandler userHandler) {
        this.userHandler = userHandler;
    }

    @Override
    public void execute() {
        this.userHandler.logUser();
    }
}