package de.jmf.application.repositories;

import de.jmf.domain.entities.User;
import de.jmf.domain.valueobjects.FitnessGoal;
import de.jmf.domain.valueobjects.Weight;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private List<User> users;

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

    public boolean checkIfEmailExists(String email) {
        for (User user : this.users) {
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

    public List<String[]> readUsers() {
        // In Progress
        List<String[]> csvData = new ArrayList<>();

        for (User user : this.users) {
            String[] line = new String[6];
            line[0] = user.getName();
            line[1] = String.valueOf(user.getAge());
            line[2] = String.valueOf(user.getWeight().getValue());
            line[3] = user.getEmail();
            line[4] = user.getGoal().getGoalType();
            line[5] = String.valueOf(user.getGoal().getTargetWeight());
            csvData.add(line);
        }

        return csvData;
    }
}
