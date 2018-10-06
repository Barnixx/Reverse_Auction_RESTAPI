package pl.barnixx.reverse_auction.infrastructure.validators;

import pl.barnixx.reverse_auction.core.repositories.IUserRepository;
import pl.barnixx.reverse_auction.infrastructure.services.IUserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUserEmailValidator implements ConstraintValidator<UniqueUserEmail, String> {

    private final IUserService IUserService;
    private final IUserRepository IUserRepository;

    public UniqueUserEmailValidator(IUserService IUserService, IUserRepository IUserRepository) {
        this.IUserService = IUserService;
        this.IUserRepository = IUserRepository;
    }

    public void initialize(UniqueUserEmail constraint) {
    }

    public boolean isValid(String mail, ConstraintValidatorContext context) {
        return IUserService.isEmailExists(mail);
    }
}