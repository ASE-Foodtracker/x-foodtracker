package de.jmf.application.usecases;

import de.jmf.application.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class RegisterUser {
    private UserRepository userRepository;

    public RegisterUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void setup(List<String[]> users) {
        userRepository.loadUsers(users);
    }

    public void execute() {

    }
}
