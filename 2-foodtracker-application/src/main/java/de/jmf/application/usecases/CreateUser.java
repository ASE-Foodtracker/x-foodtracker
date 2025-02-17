package de.jmf.application.usecases;

import de.jmf.application.exceptions.duplicateException;
import de.jmf.application.repositories.UserRepository;
import de.jmf.domain.valueobjects.FitnessGoal;
import de.jmf.domain.entities.User;
import de.jmf.domain.valueobjects.Weight;

import java.util.List;

public class CreateUser {
    private final UserRepository userRepository;

    public CreateUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void setup(List<String[]> users) {
        userRepository.loadUsers(users);
    }

    public void execute(String mail, String name, Integer age, Weight weight, FitnessGoal goal) {
        // probably should check if inputs are correct
        // check if email already exists
        if (userRepository.checkIfEmailExists(mail)) {
            // throw exception
            throw new duplicateException("A user with this mail already exists.");
        }
        // create user
        User user = new User(name, age, weight, mail, goal);
        userRepository.addUserToList(user);
    }
}
