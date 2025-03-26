package de.jmf.adapters.actions;

import de.jmf.adapters.handlers.UserHandler;

public class HandleUserLogoutAction implements Action {
    private final UserHandler userHandler;

    public HandleUserLogoutAction(UserHandler userHandler) {
        this.userHandler = userHandler;
    }

    @Override
    public void execute() {
        this.userHandler.logOut();
    }
}