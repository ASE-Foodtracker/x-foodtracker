package de.jmf.application.repositories;

import de.jmf.domain.entities.User;
import de.jmf.domain.valueobjects.FitnessGoal;
import de.jmf.domain.valueobjects.Weight;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {
    private final List<User> userList;
    private User activeUser;

    public UserRepository() {
        this.userList = new ArrayList<>();
    }

    // Single User

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

    // ---- User List

    public void setUserList(List<String[]> users) {
        users.remove(0);
        for (String[] line : users) {
            String name = line[0];
            int age = Integer.parseInt(line[1]);
            Weight weight = new Weight(Double.parseDouble(line[2]));
            Weight targetWeight = new Weight(Double.parseDouble(line[5]));
            String mail = line[3];
            FitnessGoal goal = new FitnessGoal(line[4], weight, targetWeight);
            User user = new User(name, age, weight, mail, goal);
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
