package de.jmf.application.repositories;

import de.jmf.domain.entities.User;
import de.jmf.domain.valueobjects.FitnessGoal;
import de.jmf.domain.valueobjects.Weight;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {
    private List<User> users;
    private User user;

    public UserRepository() {
        this.users = new ArrayList<>();
    }

    public void loadUsers(List<String[]> users) {
        users.remove(0);
        for (String[] line : users) {
            String name = line[0];
            int age = Integer.parseInt(line[1]);
            Weight weight = new Weight(Double.parseDouble(line[2]));
            Weight targetWeight = new Weight(Double.parseDouble(line[5]));
            String mail = line[3];
            FitnessGoal goal = new FitnessGoal(line[4], weight, targetWeight);
            User user = new User(name, age, weight, mail, goal);
            this.users.add(user);
        }
        System.out.println("");
    }

    public boolean checkIfEmailExists(String mail) {
        for (User user : this.users) {
            if (user.getEmail().equals(mail)) {
                return true;
            }
        }
        return false;
    }

    public Optional<User> getUserByMail(String mail) {
        return this.users.stream()
                .filter(user -> user.getEmail().equals(mail))
                .findFirst();
    }

    public User getActiveUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void addUserToList(User user) {
        this.users.add(user);
    }

    public List<User> readUsers() {
        // In Progress
        return this.users;
    }
}
