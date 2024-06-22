package umc.spring.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidPageValidator implements ConstraintValidator<ValidPage, Integer> {
    @Override
    public boolean isValid(Integer page, ConstraintValidatorContext context) {
        return page > 0;
    }
}
