package pl.barnixx.reverse_auction.infrastructure.commands;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class CommandDispatcher implements ICommandDispatcher {

    private final ApplicationContext context;

    public CommandDispatcher(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public <T extends ICommand> void Dispatch(T command) {

        if (command == null) {
            throw new NullPointerException("Command can not be null.");
        }

        /*TODO: In future try to make it simple and safer
        To resolve generic bean of CommandHandler, get a class name of Command<T> class + "Handler". You must also create CommandHandler class.
        CommandHandler have to implements generic ICommandHandler<T> interface. You must also remember of the naming conventions.
        For example:
        CreateUser CreateUser implements ICommand // This is a command class
        CreateUserHandler implements ICommandHandler<CreateUser> // This is a CommandHandler class for CreateUser command
         */
        @SuppressWarnings("unchecked")
        ICommandHandler<T> handler = context.getBean(command.getClass().getSimpleName() + "Handler", ICommandHandler.class);
//        ICommandHandler<T> handler = context.getBean(ICommandHandler.class, command); // It doesn't works if you have more than one implementation of ICommandHandler<T>

        handler.handle(command);

    }
}
