package pl.barnixx.reverse_auction.infrastructure.commands;


public interface ICommandHandler<T extends ICommand> {

    void handle(T command);
}
