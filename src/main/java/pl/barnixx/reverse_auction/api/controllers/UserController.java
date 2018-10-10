package pl.barnixx.reverse_auction.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.barnixx.reverse_auction.infrastructure.DTO.UserDTO;
import pl.barnixx.reverse_auction.infrastructure.commands.ICommandDispatcher;
import pl.barnixx.reverse_auction.infrastructure.commands.users.CreateUser;
import pl.barnixx.reverse_auction.infrastructure.services.IUserService;
import pl.barnixx.reverse_auction.infrastructure.validators.UserRegisterValidationGroup;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final ICommandDispatcher commandDispatcher;
    private final IUserService userService;

    public UserController(ICommandDispatcher commandDispatcher, IUserService userService) {
        this.commandDispatcher = commandDispatcher;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> get(@PathVariable long id) {
        UserDTO user = userService.findById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> post(@Validated(UserRegisterValidationGroup.class) @RequestBody CreateUser command) {

        commandDispatcher.Dispatch(command);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
