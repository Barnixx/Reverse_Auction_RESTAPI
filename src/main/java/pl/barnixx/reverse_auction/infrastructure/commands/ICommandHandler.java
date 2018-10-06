package pl.barnixx.reverse_auction.infrastructure.commands;

import org.springframework.stereotype.Component;

@Component
public interface ICommandHandler<T extends ICommand> {

    void handle(T command);
}
