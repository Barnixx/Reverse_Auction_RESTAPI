package pl.barnixx.reverse_auction.infrastructure.commands.users;

import pl.barnixx.reverse_auction.infrastructure.commands.ICommand;

public class CreateUser implements ICommand {

    public String username;
    public String email;
    public String password;
    public String repeatPassword;

}
