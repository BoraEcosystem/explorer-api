package com.boraecosystem.explorer.browser.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AppIdValidator.class)
public @interface AppConstraint {
    String message() default "App id should be defined in the properties.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

