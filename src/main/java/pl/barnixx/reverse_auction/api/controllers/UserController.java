package pl.barnixx.reverse_auction.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.barnixx.reverse_auction.infrastructure.commands.ICommandDispatcher;
import pl.barnixx.reverse_auction.infrastructure.commands.users.CreateUser;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final ICommandDispatcher commandDispatcher;

    public UserController(ICommandDispatcher commandDispatcher) {
        this.commandDispatcher = commandDispatcher;
    }

    @PostMapping("/add")
    public ResponseEntity post(@RequestBody CreateUser command) {

        commandDispatcher.Dispatch(command);

        return new ResponseEntity(HttpStatus.CREATED);
    }
}
