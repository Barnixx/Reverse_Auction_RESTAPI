package pl.barnixx.reverse_auction.api.controllers;

import pl.barnixx.reverse_auction.infrastructure.commands.ICommandDispatcher;

public abstract class BaseController {

    final ICommandDispatcher commandDispatcher;

    protected BaseController(ICommandDispatcher commandDispatcher) {
        this.commandDispatcher = commandDispatcher;
    }
}
