package de.jmf.application.usecases;

import de.jmf.application.exceptions.duplicateException;
import de.jmf.application.repositories.UserRepository;
import de.jmf.domain.valueobjects.FitnessGoal;
import de.jmf.domain.entities.User;
import de.jmf.domain.valueobjects.Weight;

import java.util.List;

public class CreateUser {
    private UserRepository userRepository;

    public CreateUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void setup(List<String[]> users) {
        userRepository.loadUsers(users);
    }

    public User execute(String email, String name, Integer age, Weight weight, FitnessGoal goal) {
        // probably should check if inputs are correct
        // check if email already exists
        if (userRepository.checkIfEmailExists(email)) {
            // throw exception
            throw new duplicateException("User already exists");
        }
        // create user
        User user = new User(name, age, weight, email, goal);
        userRepository.userAdded(user);

        return user;
    }
}
