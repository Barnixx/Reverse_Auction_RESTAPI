package pl.barnixx.reverse_auction.infrastructure.handlers.users;

import org.springframework.stereotype.Component;
import pl.barnixx.reverse_auction.infrastructure.commands.ICommandHandler;
import pl.barnixx.reverse_auction.infrastructure.commands.users.CreateUser;
import pl.barnixx.reverse_auction.infrastructure.services.IUserService;

@Component("CreateUserHandler")
public class CreateUserHandler implements ICommandHandler<CreateUser> {

    private final IUserService IUserService;

    public CreateUserHandler(IUserService IUserService) {
        this.IUserService = IUserService;
    }

    @Override
    public void handle(CreateUser command) {
        IUserService.Register(command.getEmail(), command.getUsername(), command.getPassword());
    }
}
