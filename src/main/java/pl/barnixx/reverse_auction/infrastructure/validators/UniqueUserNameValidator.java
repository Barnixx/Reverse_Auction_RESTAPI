package pl.barnixx.reverse_auction.infrastructure.validators;

import pl.barnixx.reverse_auction.infrastructure.services.IUserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUserNameValidator implements ConstraintValidator<UniqueUserName, String> {

    final private IUserService IUserService;

    public UniqueUserNameValidator(IUserService IUserService) {
        this.IUserService = IUserService;
    }

    public void initialize(UniqueUserName constraint) {
    }

    public boolean isValid(String username, ConstraintValidatorContext context) {
        return IUserService.isUsernameExists(username);
    }
}
