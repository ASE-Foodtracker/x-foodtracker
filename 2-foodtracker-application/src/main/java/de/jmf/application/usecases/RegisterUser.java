package de.jmf.application.usecases;

import de.jmf.application.repositories.UserRepository;
import de.jmf.domain.entities.User;

import java.util.List;
import java.util.Optional;

public class RegisterUser {
    private final UserRepository userRepository;

    public RegisterUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void setup(List<String[]> users) {
        userRepository.loadUsers(users);
    }

    public void execute(String mail) {
        try {
            Optional<User> user = userRepository.getUserByMail(mail);
            user.ifPresent(userRepository::setUser);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
