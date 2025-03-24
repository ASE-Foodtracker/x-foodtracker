package de.jmf.application.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import de.jmf.domain.entities.User;
import de.jmf.domain.valueobjects.FitnessGoal;
import de.jmf.domain.valueobjects.Weight;

public class UserRepository {
    private final List<User> userList;
    private User activeUser;

    public UserRepository() {
        this.userList = new ArrayList<>();
    }

    public void setUser(User user) {
        this.activeUser = user;
    }

    public Optional<User> getUserByMail(String mail) {
        return this.userList.stream()
                .filter(user -> user.getEmail().equals(mail))
                .findFirst();
    }

    public User getActiveUser() {
        return this.activeUser;
    }

    public void logOut() {
        this.activeUser = null;
    }

    public void setUserList(List<String[]> users) {
        for (String[] line : users) {
            String name = line[0];
            int age = Integer.parseInt(line[1]);
            Weight targetWeight = new Weight(Double.parseDouble(line[4]));
            String mail = line[2];
            FitnessGoal goal = new FitnessGoal(line[3], targetWeight);
            User user = new User.Builder()
                    .setName(name)
                    .setAge(age)
                    .setEmail(mail)
                    .setGoal(goal)
                    .build();
            this.userList.add(user);
        }
    }

    public void insertIntoUserList(User user) {
        this.userList.add(user);
    }

    public List<User> getUserList() {
        return this.userList;
    }
}