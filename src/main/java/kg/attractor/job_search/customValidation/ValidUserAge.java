package kg.attractor.job_search.customValidation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserAgeValidator.class)
public @interface ValidUserAge {

    String message() default "Некорректный возраст для выбранной роли";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
