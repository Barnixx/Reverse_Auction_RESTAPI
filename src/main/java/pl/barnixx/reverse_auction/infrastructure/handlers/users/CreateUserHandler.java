package pl.barnixx.reverse_auction.infrastructure.handlers.users;

import org.springframework.stereotype.Component;
import pl.barnixx.reverse_auction.infrastructure.commands.ICommandHandler;
import pl.barnixx.reverse_auction.infrastructure.commands.users.CreateUser;
import pl.barnixx.reverse_auction.infrastructure.services.UserService;

@Component("CreateUserHandler")
public class CreateUserHandler implements ICommandHandler<CreateUser> {

    private final UserService userService;

    public CreateUserHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void handle(CreateUser command) {
        userService.Register(command.getEmail(), command.getUsername(), command.getPassword());
    }
}
