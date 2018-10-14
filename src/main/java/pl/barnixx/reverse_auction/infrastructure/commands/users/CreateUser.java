package pl.barnixx.reverse_auction.infrastructure.commands.users;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import pl.barnixx.reverse_auction.infrastructure.commands.ICommand;
import pl.barnixx.reverse_auction.infrastructure.validators.ConfirmPassword;
import pl.barnixx.reverse_auction.infrastructure.validators.UniqueUserEmail;
import pl.barnixx.reverse_auction.infrastructure.validators.UniqueUserName;
import pl.barnixx.reverse_auction.infrastructure.validators.UserRegisterValidationGroup;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ConfirmPassword(groups = UserRegisterValidationGroup.class)
public class CreateUser implements ICommand {

    @UniqueUserName
    @NotBlank
    @Length(min = 5, max = 20)
    private String username;

    @UniqueUserEmail
    @NotBlank(message = "Email nie możę być pusty")
    @Email(message = "Wprowadź poprawny adres email")
    private String email;

    @NotBlank(message = "Hasło nie może być puste")
    @Column(length = 200)
    private String password;

    private String repeatPassword;

}
