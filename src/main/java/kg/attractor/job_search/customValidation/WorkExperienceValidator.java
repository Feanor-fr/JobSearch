package kg.attractor.job_search.customValidation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import kg.attractor.job_search.utils.UserHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkExperienceValidator implements ConstraintValidator<ValidWorkExperienceYears, Integer> {
    private final UserHelper userHelper;

    @Override
    public boolean isValid(Integer years, ConstraintValidatorContext context) {

        if (years == null) {
            return true;
        }

        Integer userAge = userHelper.getAuthUser().getAge();

       return userAge > years;
    }
}
