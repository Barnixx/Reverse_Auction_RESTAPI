package pl.barnixx.reverse_auction.commands;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import pl.barnixx.reverse_auction.infrastructure.commands.CommandDispatcher;
import pl.barnixx.reverse_auction.infrastructure.commands.ICommandDispatcher;
import pl.barnixx.reverse_auction.infrastructure.commands.ICommandHandler;
import pl.barnixx.reverse_auction.infrastructure.commands.users.CreateUser;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class CommandDispatcherTests {

    private ICommandDispatcher commandDispatcher;

    @Mock
    private ApplicationContext context;
    @Mock
    private ICommandHandler<CreateUser> createUserHandler;

    @Before
    public void setUp() {
        commandDispatcher = new CommandDispatcher(context);
    }

    @Test
    public void given_createUser_then_invoke_handle() {
        //given
        CreateUser createUser = new CreateUser();
        createUser.setUsername("user1");
        createUser.setEmail("user1@email.com");
        createUser.setPassword("secret");
        createUser.setRepeatPassword("secret");
        when(context.getBean(ICommandHandler.class, createUser)).thenReturn(createUserHandler);
        //when
        commandDispatcher.Dispatch(createUser);

        //then
        verify(context, times(1)).getBean(ICommandHandler.class, createUser);
    }

    @Test(expected = NullPointerException.class)
    public void given_null_then_throw_exception() {
        commandDispatcher.Dispatch(null);
    }
}
