package pl.barnixx.reverse_auction.infrastructure.validators;

import pl.barnixx.reverse_auction.infrastructure.services.IUserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUserEmailValidator implements ConstraintValidator<UniqueUserEmail, String> {

    private final IUserService IUserService;

    public UniqueUserEmailValidator(IUserService IUserService) {
        this.IUserService = IUserService;
    }

    public void initialize(UniqueUserEmail constraint) {
    }

    public boolean isValid(String mail, ConstraintValidatorContext context) {

        return !IUserService.isEmailExists(mail);
    }
}