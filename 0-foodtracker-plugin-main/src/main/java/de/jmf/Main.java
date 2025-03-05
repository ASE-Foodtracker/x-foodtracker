package de.jmf;

import de.jmf.adapters.ConsoleAdapter;
import de.jmf.application.repositories.GymPlanRepository;
import de.jmf.application.repositories.ProgressRepository;
import de.jmf.application.repositories.UserRepository;
import de.jmf.application.usecases.CreateGymPlan;
import de.jmf.application.usecases.progress.LoadWeight;
import de.jmf.application.usecases.progress.SaveWeight;
import de.jmf.application.usecases.progress.TrackWeight;
import de.jmf.application.usecases.user.CreateUser;
import de.jmf.application.usecases.user.LogOutUser;
import de.jmf.application.usecases.user.LogUser;
import de.jmf.application.usecases.user.RegisterUser;
import de.jmf.application.usecases.user.SaveUser;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        GymPlanRepository gymPlanRepository = new GymPlanRepository();
        ProgressRepository progressRepository = new ProgressRepository();

        CreateUser createUser = new CreateUser(userRepository);
        RegisterUser login = new RegisterUser(userRepository);
        LogUser logUser = new LogUser(userRepository);
        SaveUser saveUser = new SaveUser(userRepository);
        LogOutUser logoutUser = new LogOutUser(userRepository);
        
        CreateGymPlan createGymplan = new CreateGymPlan(gymPlanRepository);
        TrackWeight trackWeight = new TrackWeight(progressRepository);
        SaveWeight saveWeight = new SaveWeight(progressRepository);
        LoadWeight loadWeight = new LoadWeight(progressRepository);

        ConsoleAdapter console = new ConsoleAdapter(createUser, login, logUser, saveUser, createGymplan, trackWeight, saveWeight,
                loadWeight, logoutUser);
        // Start the application
        console.running();
    }
}
