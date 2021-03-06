package pl.barnixx.reverse_auction.infrastructure.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CategoryExistsValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CategoryExists {

    String message() default "{validators.CategoryExists.error.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
