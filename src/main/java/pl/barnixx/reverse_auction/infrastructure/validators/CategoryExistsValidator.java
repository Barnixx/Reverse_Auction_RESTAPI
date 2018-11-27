package pl.barnixx.reverse_auction.infrastructure.validators;

import pl.barnixx.reverse_auction.infrastructure.services.CategoryService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CategoryExistsValidator implements ConstraintValidator<CategoryExists, Long> {

    private final CategoryService categoryService;

    public CategoryExistsValidator(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public void initialize(CategoryExists constraint) {

    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return false;
    }
}
