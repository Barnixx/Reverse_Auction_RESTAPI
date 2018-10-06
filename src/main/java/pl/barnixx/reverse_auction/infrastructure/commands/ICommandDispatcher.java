package pl.barnixx.reverse_auction.infrastructure.commands;


public interface ICommandDispatcher {

    <T extends ICommand> void Dispatch(T command);
}
