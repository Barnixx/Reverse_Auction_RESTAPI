package pl.barnixx.reverse_auction.infrastructure.validators;

import pl.barnixx.reverse_auction.infrastructure.commands.users.CreateUser;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ConfirmPasswordValidator implements ConstraintValidator<ConfirmPassword, CreateUser> {
    @Override
    public void initialize(ConfirmPassword confirmPassword) {

    }

    @Override
    public boolean isValid(CreateUser user, ConstraintValidatorContext context) {
        if (!user.getPassword().equals(user.getRepeatPassword())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addPropertyNode("password").addConstraintViolation();
            return false;
        }
        return true;
    }
}