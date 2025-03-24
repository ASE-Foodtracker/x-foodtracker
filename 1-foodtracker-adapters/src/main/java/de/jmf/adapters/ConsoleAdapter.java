package de.jmf.adapters;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import de.jmf.adapters.actions.HandleGymPlanAction;
import de.jmf.adapters.actions.HandleMealManagementAction;
import de.jmf.adapters.actions.HandleQuitAction;
import de.jmf.adapters.actions.HandleSavingAction;
import de.jmf.adapters.actions.HandleUserLoginAction;
import de.jmf.adapters.actions.HandleUserLogoutAction;
import de.jmf.adapters.actions.HandleWeightEntryAction;
import de.jmf.adapters.handlers.GymPlanHandler;
import de.jmf.adapters.handlers.ProgressHandler;
import de.jmf.adapters.handlers.UserHandler;
import de.jmf.adapters.helper.InputReader;
import de.jmf.adapters.helper.LoopUntilSuccessful;
import de.jmf.adapters.helper.Strings;
import de.jmf.adapters.io.CSVWriter;
import de.jmf.adapters.menus.HomeScreenOption;
import de.jmf.adapters.menus.MainMenuOption;
import de.jmf.adapters.menus.PerformMenuOption;
import de.jmf.adapters.menus.PrintMenus;
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

public class ConsoleAdapter {

    private final InputReader inputValidator;
    private final PrintMenus printMenus;
    private final PerformMenuOption performMenuOption;
    private final LoopUntilSuccessful loopUntilSuccessful;
    private final UserHandler userHandler;
    private final ProgressHandler progressHandler;
    private final GymPlanHandler gymPlanHandler;

    public ConsoleAdapter(
            CreateUser createUser,
            LoginUser login,
            GetActiveUser logUser,
            SaveUser saveUser,
            CreateGymPlan createGymPlan,
            TrackWeight trackWeight,
            SaveWeight saveWeight,
            LoadWeight loadWeight,
            LogOutUser logOutUser,
            SaveMeal saveMeal,
            GetTodaysMeals logTodaysMeals,
            RemoveMeal removeMeal,
            GetAllMeals getAllMeals) {

        this.inputValidator = new InputReader();
        this.printMenus = new PrintMenus();
        this.performMenuOption = new PerformMenuOption();
        this.loopUntilSuccessful = new LoopUntilSuccessful();

        this.userHandler = new UserHandler(createUser, login, logUser, saveUser, logOutUser);
        this.progressHandler = new ProgressHandler(logUser, trackWeight, saveWeight, loadWeight, saveMeal,
                logTodaysMeals, removeMeal, getAllMeals);
        this.gymPlanHandler = new GymPlanHandler(createGymPlan);
    }

    public void run() {
        boolean running = true;
        init();

        Map<MainMenuOption, Runnable> mainMenuActions = new HashMap<>();

        mainMenuActions.put(MainMenuOption.QUIT, () -> new HandleQuitAction(this.userHandler, this.progressHandler).execute());
        mainMenuActions.put(MainMenuOption.DETAILS, () -> new HandleUserLoginAction(this.userHandler).execute());
        mainMenuActions.put(MainMenuOption.GYMPLAN, () -> new HandleGymPlanAction(this.userHandler, this.gymPlanHandler).execute());
        mainMenuActions.put(MainMenuOption.WEIGHT_ENTRY, () -> new HandleWeightEntryAction(this.progressHandler).execute());
        mainMenuActions.put(MainMenuOption.MEAL_MANAGEMENT, () -> new HandleMealManagementAction(this.progressHandler).execute());
        mainMenuActions.put(MainMenuOption.SAVE, () -> new HandleSavingAction(this.userHandler, this.progressHandler).execute());
        mainMenuActions.put(MainMenuOption.LOGOUT, () -> {
            new HandleUserLogoutAction(this.userHandler).execute();
            homeScreen();
        });

        while (running) {
            try {
                this.printMenus.printMainMenu();
                int option = this.inputValidator.getInt(Strings.ENTER_THE_NUMBER_OF_THE_ACTION);
                MainMenuOption selected = MainMenuOption.fromInt(option);
                this.performMenuOption.execute(mainMenuActions, selected);
                if(selected == MainMenuOption.QUIT){
                    running = false;
                }
            } catch (Exception e) {
                System.out.println(Strings.AN_ERROR_OCCURED + " " + e.getMessage());
            }
        }
    }

    private void init() {
        homeScreen();
        progressHandler.loadProgress();
    }

    private void homeScreen() {
        Path usersPath = Paths.get("").resolve("data").resolve("output").resolve("users.csv");
        CSVWriter usersWriter = new CSVWriter(usersPath);
        usersWriter.createFile("name;age;mail;goalType;targetWeight");
        this.printMenus.printHomeScreen();

        Map<HomeScreenOption, Runnable> homeScreenActions = new HashMap<>();

        homeScreenActions.put(HomeScreenOption.LOGIN, () -> this.loopUntilSuccessful.execute(() -> this.userHandler.login()));
        homeScreenActions.put(HomeScreenOption.REGISTER, () -> this.loopUntilSuccessful.execute(() -> this.userHandler.createUser()));

        try {
            int option = this.inputValidator.getInt(Strings.ENTER_THE_NUMBER_OF_THE_ACTION);
            HomeScreenOption selected = HomeScreenOption.fromInt(option);
            this.performMenuOption.execute(homeScreenActions, selected);
        } catch (Exception e) {
            System.out.println(Strings.AN_ERROR_OCCURED + " " + e.getMessage());
        }
    }
}
