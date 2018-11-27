package pl.barnixx.reverse_auction.infrastructure.validators;

import pl.barnixx.reverse_auction.infrastructure.services.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUserEmailValidator implements ConstraintValidator<UniqueUserEmail, String> {

    private final UserService UserService;

    public UniqueUserEmailValidator(UserService IUserService) {
        this.UserService = IUserService;
    }

    public void initialize(UniqueUserEmail constraint) {
    }

    public boolean isValid(String mail, ConstraintValidatorContext context) {

        return !UserService.isEmailExists(mail);
    }
}