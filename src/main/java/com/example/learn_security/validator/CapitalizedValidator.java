package com.example.learn_security.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

public class CapitalizedValidator implements ConstraintValidator<CapitalizedConstraint, String> {
    @Override
    public void initialize(CapitalizedConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(!StringUtils.hasLength(value)) return Boolean.FALSE;
        if(!Character.isUpperCase(value.charAt(0))) return Boolean.FALSE;

        return Boolean.TRUE;
    }
}