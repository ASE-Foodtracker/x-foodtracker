package de.jmf.application.usecases.user;

import de.jmf.application.repositories.UserRepository;
import de.jmf.domain.entities.User;

import java.util.List;
import java.util.Optional;

public class LoginUser {
    private final UserRepository userRepository;

    public LoginUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void setup(List<String[]> users) {
        users.remove(0);
        userRepository.setUserList(users);
    }

    public boolean execute(String mail) {
        try {
            Optional<User> user = userRepository.getUserByMail(mail);
            user.ifPresent(userRepository::setUser);
            return user.isPresent();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
