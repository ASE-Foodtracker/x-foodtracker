package de.jmf.application.usecases.user;

import java.util.List;
import java.util.Optional;

import de.jmf.application.exceptions.duplicateException;
import de.jmf.application.repositories.UserRepository;
import de.jmf.domain.entities.User;
import de.jmf.domain.valueobjects.FitnessGoal;

public class CreateUser {
    private final UserRepository userRepository;

    public CreateUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void setup(List<String[]> users) {
        users.remove(0);
        userRepository.setUserList(users);
    }

    public void execute(String mail, String name, Integer age, FitnessGoal goal) {
        Optional<User> optionalUser = userRepository.getUserByMail(mail);
        if (optionalUser.isPresent()) {
            // throw exception
            throw new duplicateException("A user with this mail already exists.");
        } else {
            User user = new User.Builder()
                    .setName(name)
                    .setAge(age)
                    .setEmail(mail)
                    .setGoal(goal)
                    .build();
            userRepository.insertIntoUserList(user);
            userRepository.setUser(user);
        }
    }
}
