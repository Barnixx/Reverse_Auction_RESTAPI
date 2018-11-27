package pl.barnixx.reverse_auction.handlers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import pl.barnixx.reverse_auction.infrastructure.commands.users.CreateUser;
import pl.barnixx.reverse_auction.infrastructure.handlers.users.CreateUserHandler;
import pl.barnixx.reverse_auction.infrastructure.services.UserService;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class CreateUserHandlerTests {

    private CreateUserHandler userHandler;

    @Mock
    private UserService userService;

    @Before
    public void setUp() {
        userHandler = new CreateUserHandler(userService);
    }

    @Test
    public void given_createUser_command_then_invoke_register() {

        CreateUser command = new CreateUser();
        command.setUsername("user1");
        command.setEmail("user1@email.com");
        command.setPassword("secret");

        userHandler.handle(command);

        verify(userService, times(1)).Register(
                command.getEmail(),
                command.getUsername(),
                command.getPassword());
    }
}
