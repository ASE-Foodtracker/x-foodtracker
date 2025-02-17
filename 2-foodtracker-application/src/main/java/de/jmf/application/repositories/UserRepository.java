package de.jmf.application.repositories;

import de.jmf.domain.entities.User;

import java.util.ArrayList;

public class UserRepository {
    private ArrayList<User> users;
    private String path;

    public UserRepository(String path) {
        this.path = path;
    }

    public void loadUsers(ArrayList<User> users) {
        this.users = users;
    }

    public boolean checkIfEmailExists(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public boolean userAdded(User user) {
        this.users.add(user);
        return true;
    }
}
