package de.jmf;

import de.jmf.adapters.ConsoleAdapter;
import de.jmf.application.repositories.UserRepository;
import de.jmf.application.usecases.CreateUser;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        CreateUser createUser = new CreateUser(userRepository);
        ConsoleAdapter console = new ConsoleAdapter(createUser);
        // Start the application
        console.running();
    }
}