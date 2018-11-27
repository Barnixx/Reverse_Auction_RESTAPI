package pl.barnixx.reverse_auction.infrastructure.validators;

import pl.barnixx.reverse_auction.infrastructure.services.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUserNameValidator implements ConstraintValidator<UniqueUserName, String> {

    final private UserService UserService;

    public UniqueUserNameValidator(UserService IUserService) {
        this.UserService = IUserService;
    }

    public void initialize(UniqueUserName constraint) {
    }

    public boolean isValid(String username, ConstraintValidatorContext context) {
        boolean result = UserService.isUsernameExists(username);
        return !result;
    }
}
