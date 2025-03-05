package de.jmf;

import de.jmf.adapters.ConsoleAdapter;
import de.jmf.application.repositories.GymPlanRepository;
import de.jmf.application.repositories.UserRepository;
import de.jmf.application.usecases.CreateGymPlan;
import de.jmf.application.usecases.user.CreateUser;
import de.jmf.application.usecases.user.LogUser;
import de.jmf.application.usecases.user.RegisterUser;
import de.jmf.application.usecases.user.SaveUser;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        GymPlanRepository gymPlanRepository = new GymPlanRepository();

        CreateUser createUser = new CreateUser(userRepository);
        RegisterUser login = new RegisterUser(userRepository);
        LogUser logUser = new LogUser(userRepository);
        SaveUser saveUser = new SaveUser(userRepository);
        CreateGymPlan createGymplan = new CreateGymPlan(gymPlanRepository);


        ConsoleAdapter console = new ConsoleAdapter(createUser, login, logUser, saveUser, createGymplan);
        // Start the application
        console.running();
    }

}