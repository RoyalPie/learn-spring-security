package com.example.learn_security.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CapitalizedValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CapitalizedConstraint {
    String message() default "Chữ đầu tiên phải được in hoa!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}