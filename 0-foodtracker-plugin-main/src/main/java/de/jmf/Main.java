package de.jmf;

import de.jmf.adapters.ConsoleAdapter;
import de.jmf.application.repositories.UserRepository;
import de.jmf.application.usecases.CreateUser;
import de.jmf.application.usecases.RegisterUser;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();

        CreateUser createUser = new CreateUser(userRepository);
        RegisterUser login = new RegisterUser(userRepository);

        ConsoleAdapter console = new ConsoleAdapter(createUser, login);
        // Start the application
        console.running();
    }

}