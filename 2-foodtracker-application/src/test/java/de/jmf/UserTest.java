package de.jmf;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.jmf.application.exceptions.duplicateException;
import de.jmf.application.repositories.UserRepository;
import de.jmf.application.usecases.user.CreateUser;
import de.jmf.application.usecases.user.GetActiveUser;
import de.jmf.application.usecases.user.LogOutUser;
import de.jmf.application.usecases.user.LoginUser;
import de.jmf.application.usecases.user.SaveUser;
import de.jmf.domain.entities.User;
import de.jmf.domain.valueobjects.FitnessGoal;
import de.jmf.domain.valueobjects.Weight;

public class UserTest {
    private UserRepository userRepository;
    private CreateUser createUser;
    private LoginUser loginUser;
    private GetActiveUser getActiveUser;
    private LogOutUser logOutUser;
    private SaveUser saveUser;

    @BeforeEach
    public void setUp() {
        userRepository = mock(UserRepository.class);
        createUser = new CreateUser(userRepository);
        loginUser = new LoginUser(userRepository);
        getActiveUser = new GetActiveUser(userRepository);
        logOutUser = new LogOutUser(userRepository);
        saveUser = new SaveUser(userRepository);
    }

    @Test
    public void testCreateUser() {
        // Arrange
        FitnessGoal goal = new FitnessGoal("gain", new Weight(70));
        when(userRepository.getUserByMail("john.doe@example.com")).thenReturn(Optional.empty());

        // Act
        createUser.execute("john.doe@example.com", "John Doe", 25, goal);

        // Assert
        verify(userRepository, times(1)).insertIntoUserList(any(User.class));
        verify(userRepository, times(1)).setUser(any(User.class));
    }

    @Test
    public void testCreateUserWithDuplicateEmail() {
        // Arrange
        when(userRepository.getUserByMail("john.doe@example.com"))
                .thenReturn(Optional.of(new User.Builder()
                        .setName("John Doe")
                        .setAge(25)
                        .setEmail("john.doe@example.com")
                        .setGoal(new FitnessGoal("gain", new Weight(70)))
                        .build()));

        // Act & Assert
        Exception exception = assertThrows(duplicateException.class, () -> {
            createUser.execute("john.doe@example.com", "Jane Doe", 30, new FitnessGoal("lose", new Weight(60)));
        });
        assertEquals("A user with this mail already exists.", exception.getMessage());
    }

    @Test
    public void testLoginUser() {
        // Arrange
        User user = new User.Builder()
                .setEmail("john.doe@example.com")
                .setName("John Doe")
                .setAge(25)
                .setGoal(new FitnessGoal("gain", new Weight(70)))
                .build();
        when(userRepository.getUserByMail("john.doe@example.com")).thenReturn(Optional.of(user));

        // Act
        boolean success = loginUser.execute("john.doe@example.com");

        // Assert
        assertTrue(success);
        verify(userRepository, times(1)).setUser(user);
    }

    @Test
    public void testGetActiveUser() {
        // Arrange
        User user = new User.Builder()
                .setName("John Doe")
                .setAge(25)
                .setEmail("john.doe@example.com")
                .setGoal(new FitnessGoal("gain", new Weight(70)))
                .build();
        when(userRepository.getActiveUser()).thenReturn(user);

        // Act
        User activeUser = getActiveUser.execute();

        // Assert
        assertNotNull(activeUser);
        assertEquals("john.doe@example.com", activeUser.getEmail());
    }

    @Test
    public void testLogOutUser() {
        // Arrange
        doNothing().when(userRepository).logOut();

        // Act
        logOutUser.execute();

        // Assert
        verify(userRepository, times(1)).logOut();
    }

    @Test
    public void testSaveUser() {
        // Arrange
        User user = new User.Builder()
                .setName("John Doe")
                .setAge(25)
                .setEmail("john.doe@example.com")
                .setGoal(new FitnessGoal("gain", new Weight(70)))
                .build();
        when(userRepository.getUserList()).thenReturn(List.of(user));

        // Act
        List<String[]> users = saveUser.execute();

        // Assert
        assertNotNull(users, "Users list should not be null");
        assertEquals(1, users.size(), "There should be exactly one user");
        assertArrayEquals(new String[]{"John Doe", "25", "john.doe@example.com", "gain", "70.0"}, users.get(0), "User data should match");
    }
}