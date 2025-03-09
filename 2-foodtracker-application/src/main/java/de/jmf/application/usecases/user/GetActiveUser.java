package de.jmf.application.usecases.user;

import de.jmf.application.repositories.UserRepository;
import de.jmf.domain.entities.User;

public class GetActiveUser {
    private final UserRepository userRepository;
    public GetActiveUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute() {
        return userRepository.getActiveUser();
    }
}
