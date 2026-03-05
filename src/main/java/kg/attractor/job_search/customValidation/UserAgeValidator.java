package kg.attractor.job_search.customValidation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import kg.attractor.job_search.dto.UserDto;

public class UserAgeValidator implements ConstraintValidator<ValidUserAge, UserDto> {

    @Override
    public boolean isValid(UserDto userDto, ConstraintValidatorContext context) {

        if (userDto == null || userDto.getRole() == null) {
            return true;
        }

        boolean valid = true;
        context.disableDefaultConstraintViolation();

        Integer role = userDto.getRole();
        Integer age = userDto.getAge();
        String surname = userDto.getSurname();

        if (role == 1) {
            if (surname == null || surname.isBlank()) {
                context.buildConstraintViolationWithTemplate(
                        "Фамилия обязательна для соискателя"
                ).addPropertyNode("surname").addConstraintViolation();
                valid = false;
            }

            if (age != null) {
                if (age < 18) {
                    context.buildConstraintViolationWithTemplate(
                            "Соискатель должен быть старше 18 лет"
                    ).addPropertyNode("age").addConstraintViolation();
                    valid = false;
                }
                if (age > 60) {
                    context.buildConstraintViolationWithTemplate(
                            "Соискатель не может быть старше 60 лет"
                    ).addPropertyNode("age").addConstraintViolation();
                    valid = false;
                }
            }
        }

        if (role == 2 && age != null) {
            if (age < 1) {
                context.buildConstraintViolationWithTemplate(
                        "Компания должна существовать хотя бы 1 год"
                ).addPropertyNode("age").addConstraintViolation();
                valid = false;
            }

            if (age > 300) {
                context.buildConstraintViolationWithTemplate(
                        "Ваша компания существует подозрительно долгое время"
                ).addPropertyNode("age").addConstraintViolation();
                valid = false;
            }
        }

        return valid;
    }
}
