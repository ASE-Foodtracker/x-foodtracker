package de.jmf;

import de.jmf.adapters.ConsoleAdapter;
import de.jmf.adapters.io.CheckExistingCsvFiles;
import de.jmf.application.repositories.GymPlanRepository;
import de.jmf.application.repositories.ProgressRepository;
import de.jmf.application.repositories.UserRepository;
import de.jmf.application.usecases.CreateGymPlan;
import de.jmf.application.usecases.progress.Meals.GetAllMeals;
import de.jmf.application.usecases.progress.Meals.GetTodaysMeals;
import de.jmf.application.usecases.progress.Meals.RemoveMeal;
import de.jmf.application.usecases.progress.Meals.SaveMeal;
import de.jmf.application.usecases.progress.Weight.LoadWeight;
import de.jmf.application.usecases.progress.Weight.SaveWeight;
import de.jmf.application.usecases.progress.Weight.TrackWeight;
import de.jmf.application.usecases.user.CreateUser;
import de.jmf.application.usecases.user.GetActiveUser;
import de.jmf.application.usecases.user.LogOutUser;
import de.jmf.application.usecases.user.LoginUser;
import de.jmf.application.usecases.user.SaveUser;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        GymPlanRepository gymPlanRepository = new GymPlanRepository();
        ProgressRepository progressRepository = new ProgressRepository();

        CreateUser createUser = new CreateUser(userRepository);
        LoginUser login = new LoginUser(userRepository);
        GetActiveUser logUser = new GetActiveUser(userRepository);
        SaveUser saveUser = new SaveUser(userRepository);
        LogOutUser logoutUser = new LogOutUser(userRepository);
        
        CreateGymPlan createGymplan = new CreateGymPlan(gymPlanRepository);
        TrackWeight trackWeight = new TrackWeight(progressRepository);
        SaveWeight saveWeight = new SaveWeight(progressRepository);
        LoadWeight loadWeight = new LoadWeight(progressRepository);

        SaveMeal saveMeal = new SaveMeal(progressRepository);
        GetTodaysMeals getTodaysMeals = new GetTodaysMeals(progressRepository);
        RemoveMeal removeMeal = new RemoveMeal(progressRepository);
        GetAllMeals getAllMeals = new GetAllMeals(progressRepository);

        ConsoleAdapter console = new ConsoleAdapter(createUser, login, logUser, saveUser, createGymplan, trackWeight, saveWeight,
                loadWeight, logoutUser, saveMeal, getTodaysMeals, removeMeal, getAllMeals);
        
        
        CheckExistingCsvFiles checkExistingCsvFiles = new CheckExistingCsvFiles();
        checkExistingCsvFiles.check();

        console.run();
    }
}
