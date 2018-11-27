package pl.barnixx.reverse_auction.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import pl.barnixx.reverse_auction.infrastructure.DTO.UserDTO;
import pl.barnixx.reverse_auction.infrastructure.commands.ICommandDispatcher;
import pl.barnixx.reverse_auction.infrastructure.commands.users.CreateUser;
import pl.barnixx.reverse_auction.infrastructure.services.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController extends BaseController {

    private final UserService userService;

    public UserController(ICommandDispatcher commandDispatcher, UserService userService) {
        super(commandDispatcher);
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

    @GetMapping
    public ResponseEntity<List<UserDTO>> get() {
        List<UserDTO> users = userService.getAll();
        if (users.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity post(@Valid @RequestBody CreateUser command, Errors errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        commandDispatcher.Dispatch(command);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
