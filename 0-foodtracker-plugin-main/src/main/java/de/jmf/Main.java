package de.jmf;

import de.jmf.adapters.ConsoleAdapter;
import de.jmf.application.repositories.UserRepository;
import de.jmf.application.usecases.user.CreateUser;
import de.jmf.application.usecases.user.LogUser;
import de.jmf.application.usecases.user.RegisterUser;
import de.jmf.application.usecases.user.SaveUser;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();

        CreateUser createUser = new CreateUser(userRepository);
        RegisterUser login = new RegisterUser(userRepository);
        LogUser logUser = new LogUser(userRepository);
        SaveUser saveUser = new SaveUser(userRepository);

        ConsoleAdapter console = new ConsoleAdapter(createUser, login, logUser, saveUser);
        // Start the application
        console.running();
    }

}