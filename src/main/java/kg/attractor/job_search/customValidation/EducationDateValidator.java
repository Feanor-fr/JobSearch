package kg.attractor.job_search.customValidation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import kg.attractor.job_search.dto.EducationInfoDto;

public class EducationDateValidator implements ConstraintValidator<ValidEducationDates, EducationInfoDto> {

    @Override
    public boolean isValid(EducationInfoDto educationInfoDto,
                           ConstraintValidatorContext context
    ) {

        if (educationInfoDto == null ||
                educationInfoDto.getStartDate() == null ||
                educationInfoDto.getEndDate() == null) {
            return true;
        }

        if (educationInfoDto.getEndDate().isBefore(educationInfoDto.getStartDate())) {

            context.disableDefaultConstraintViolation();

            context.buildConstraintViolationWithTemplate(
                            "{error.invalid.date.interval1}"
                    )
                    .addPropertyNode("endDate")
                    .addConstraintViolation();

            return false;
        }

        return true;
    }
}
