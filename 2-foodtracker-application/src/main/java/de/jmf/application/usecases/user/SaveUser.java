package de.jmf.application.usecases.user;

import de.jmf.application.repositories.UserRepository;
import de.jmf.domain.entities.User;

import java.util.ArrayList;
import java.util.List;

public class SaveUser {
    private final UserRepository userRepository;

    public SaveUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<String[]> execute() {
        // fetch users
        List<User> users = userRepository.getUserList();
        List<String[]> csvData = new ArrayList<>();

        for (User user : users) {
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
