package de.jmf.application.usecases.user;

import de.jmf.application.repositories.UserRepository;
import de.jmf.domain.entities.User;

public class LogUser {
    private final UserRepository userRepository;
    public LogUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute() {
        return userRepository.getActiveUser();
    }
}
