package de.jmf;

import de.jmf.adapters.ConsoleAdapter;
import de.jmf.application.repositories.*;
import de.jmf.application.usecases.progress.Meals.*;
import de.jmf.application.usecases.progress.Weight.*;
import de.jmf.application.usecases.user.*;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        ProgressRepository progressRepository = new ProgressRepository();

        CreateUser createUser = new CreateUser(userRepository);
        LoginUser login = new LoginUser(userRepository);
        GetActiveUser logUser = new GetActiveUser(userRepository);
        SaveUser saveUser = new SaveUser(userRepository);
        LogOutUser logoutUser = new LogOutUser(userRepository);

        TrackWeight trackWeight = new TrackWeight(progressRepository);
        SaveWeight saveWeight = new SaveWeight(progressRepository);
        LoadWeight loadWeight = new LoadWeight(progressRepository);

        SaveMeal saveMeal = new SaveMeal(progressRepository);
        GetTodaysMeals getTodaysMeals = new GetTodaysMeals(progressRepository);
        RemoveMeal removeMeal = new RemoveMeal(progressRepository);
        GetAllMeals getAllMeals = new GetAllMeals(progressRepository);

        ConsoleAdapter console = new ConsoleAdapter(createUser, login, logUser, saveUser, trackWeight, saveWeight,
                loadWeight, logoutUser, saveMeal, getTodaysMeals, removeMeal, getAllMeals);
        // Start the application
        console.running();
    }
}
