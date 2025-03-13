package de.jmf.adapters.handlers;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import de.jmf.adapters.io.CSVReader;
import de.jmf.adapters.io.CSVWriter;
import de.jmf.application.exceptions.duplicateException;
import de.jmf.application.usecases.user.CreateUser;
import de.jmf.application.usecases.user.GetActiveUser;
import de.jmf.application.usecases.user.LogOutUser;
import de.jmf.application.usecases.user.LoginUser;
import de.jmf.application.usecases.user.SaveUser;
import de.jmf.adapters.helper.InputValidator;
import de.jmf.adapters.helper.Strings;
import de.jmf.domain.entities.User;
import de.jmf.domain.valueobjects.FitnessGoal;
import de.jmf.domain.valueobjects.Weight;

public class UserHandler {

    private final CreateUser createUser;
    private final LoginUser login;
    private final GetActiveUser logUser;
    private final SaveUser saveUser;
    private final LogOutUser logOutUser;

    private final InputValidator inputValidator;

    public UserHandler(CreateUser createUser, LoginUser login, GetActiveUser logUser, SaveUser saveUser,
            LogOutUser logOutUser) {
        this.createUser = createUser;
        this.login = login;
        this.logUser = logUser;
        this.saveUser = saveUser;
        this.logOutUser = logOutUser;
        this.inputValidator = new InputValidator();
    }

    public boolean saveUser() {
        List<String[]> users = saveUser.execute();
        String[] head = new String[5];
        head[0] = "name";
        head[1] = "age";
        head[2] = "mail";
        head[3] = "goalType";
        head[4] = "targetWeight";

        users.add(0, head);
        // Clear data/output/users
        // Write Users to data/output/users
        Path outputPath = Paths.get("").resolve("data").resolve("output").resolve("users.csv");
        CSVWriter csvWriter = new CSVWriter(outputPath);
        csvWriter.clear();
        csvWriter.saveAll(users);
        return true;
    }

    public User logUser() {
        try {
            System.out.println();
            System.out.println(Strings.CURRENT_ACTIVE_USER);
            User user = logUser.execute();
            System.out.println(Strings.EMAIL + user.getEmail());
            System.out.println(Strings.NAME + user.getName());
            System.out.println(Strings.AGE + user.getAge());
            System.out.println(Strings.GOAL + user.getGoal().getGoalType());
            return user;
        } catch (Exception e) {
            System.out.println("User couldn't be fetched: " + e.getMessage());
        }
        return null;
    }

    public String getUserMail(){
        try{
            User user = logUser.execute();
            return user.getEmail();
        } catch (Exception e){
            System.out.println("User couldn't be fetched: " + e.getMessage());
            return null;
        }
    }

    public String getUserFitnessGoal(){
        try{
            User user = logUser.execute();
            return user.getGoal().getGoalType();
        } catch (Exception e){
            System.out.println("User couldn't be fetched: " + e.getMessage());
            return null;
        }
    }

    public boolean login() {
        try {
            System.out.println();
            System.out.println(Strings.REGISTRATION);
            String mail = this.inputValidator.getString(Strings.PLEASE_ENTER_YOUR_EMAIL);
            // load users.csv
            Path currentPath = Paths.get("").resolve("data").resolve("output").resolve("users.csv");
            login.setup(new CSVReader(currentPath).readAll());
            // user login
            boolean success = login.execute(mail);
            if (success) {
                System.out.println(Strings.YOU_SUCCESSFULLY_LOGGED_INTO_YOUR_ACCOUNT);
                return true;
            } else {
                System.out.println(Strings.THIS_USER_DOESNT_EXIST_PLEASE_TRY_AGAIN);
                return false;
            }
        } catch (Exception e) {
            System.out.print(Strings.AN_ERROR_OCCURED + e.getMessage());
            System.out.println();
            return false;
        }
    }

    public boolean createUser() {
        try {
            System.out.println();
            System.out.println(Strings.CREATING_NEW_ACCOUNT);
            String mail = this.inputValidator.getString(Strings.EMAIL);
            String name = this.inputValidator.getString(Strings.NAME);
            int age = this.inputValidator.getInt(Strings.AGE);
            String goalType = this.inputValidator.getString(Strings.DO_YOU_WANT_TO_GAIN_OR_LOOSE);
            Weight weightF = new Weight(this.inputValidator.getDouble(Strings.PLEASE_ENTER_YOUR_TARGET_WEIGHT));
            FitnessGoal goal = new FitnessGoal(goalType, weightF);
            // load user.csv
            Path currentPath = Paths.get("").resolve("data").resolve("output").resolve("users.csv");
            createUser.setup(new CSVReader(currentPath).readAll());
            // create user
            createUser.execute(mail, name, age, goal);
            System.out.println(Strings.YOU_SUCCESSFULLY_CREATED_A_NEW_ACCOUNT);
            return true;
        } catch (duplicateException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.print(Strings.AN_ERROR_OCCURED + e.getMessage());
            return false;
        }
    }

    public void logOut() {
        logOutUser.execute();
        System.out.println();
    }
}