package de.jmf.application.usecases.user;

import de.jmf.application.repositories.UserRepository;

public class LogOutUser {
    private final UserRepository userRepository;

    public LogOutUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute() {
        userRepository.logOut();
    }
}
