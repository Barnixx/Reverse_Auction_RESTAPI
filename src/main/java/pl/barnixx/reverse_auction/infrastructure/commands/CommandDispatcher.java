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
            throw new NullPointerException();
        }

        //TODO: In future try to make it simple and safer
        ICommandHandler<T> handler = context.getBean(command.getClass().getSimpleName() + "Handler", ICommandHandler.class);

        handler.handle(command);

    }
}
