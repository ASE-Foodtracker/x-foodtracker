package de.jmf;

import de.jmf.application.repositories.UserRepository;
import de.jmf.application.usecases.user.CreateUser;
import de.jmf.application.usecases.user.GetActiveUser;
import de.jmf.application.usecases.user.LoginUser;
import de.jmf.application.usecases.user.LogOutUser;
import de.jmf.application.usecases.user.SaveUser;
import de.jmf.domain.entities.User;
import de.jmf.domain.valueobjects.FitnessGoal;
import de.jmf.domain.valueobjects.Weight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    private UserRepository userRepository;
    private CreateUser createUser;
    private LoginUser loginUser;
    private GetActiveUser getActiveUser;
    private LogOutUser logOutUser;
    private SaveUser saveUser;

    @BeforeEach
    public void setUp() {
        userRepository = new UserRepository();
        createUser = new CreateUser(userRepository);
        loginUser = new LoginUser(userRepository);
        getActiveUser = new GetActiveUser(userRepository);
        logOutUser = new LogOutUser(userRepository);
        saveUser = new SaveUser(userRepository);
    }

    @Test
    public void testCreateUser() {
        FitnessGoal goal = new FitnessGoal("gain", new Weight(70));
        createUser.execute("john.doe@example.com", "John Doe", 25, goal);

        User user = userRepository.getUserByMail("john.doe@example.com").orElse(null);
        assertNotNull(user);
        assertEquals("John Doe", user.getName());
        assertEquals(25, user.getAge());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals(goal, user.getGoal());
    }

    @Test
    public void testLoginUser() {
        FitnessGoal goal = new FitnessGoal("gain", new Weight(70));
        createUser.execute("john.doe@example.com", "John Doe", 25, goal);

        boolean success = loginUser.execute("john.doe@example.com");
        assertTrue(success);

        User activeUser = getActiveUser.execute();
        assertNotNull(activeUser);
        assertEquals("john.doe@example.com", activeUser.getEmail());
    }

    @Test
    public void testLogOutUser() {
        FitnessGoal goal = new FitnessGoal("gain", new Weight(70));
        createUser.execute("john.doe@example.com", "John Doe", 25, goal);
        loginUser.execute("john.doe@example.com");

        logOutUser.execute();
        User activeUser = getActiveUser.execute();
        assertNull(activeUser);
    }

    @Test
    public void testSaveUser() {
        FitnessGoal goal = new FitnessGoal("gain", new Weight(70));
        createUser.execute("john.doe@example.com", "John Doe", 25, goal);

        List<String[]> users = saveUser.execute();
        assertNotNull(users);
        assertEquals(1, users.size());
        assertEquals("John Doe", users.get(0)[0]);
        assertEquals("25", users.get(0)[1]);
        assertEquals("john.doe@example.com", users.get(0)[2]);
        assertEquals("gain", users.get(0)[3]);
        assertEquals("70.0", users.get(0)[4]);
    }
}