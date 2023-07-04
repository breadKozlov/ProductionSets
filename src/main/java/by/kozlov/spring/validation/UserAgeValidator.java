package by.kozlov.spring.validation;

import by.kozlov.spring.dto.UserCreateEditDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class UserAgeValidator implements ConstraintValidator<UserAge, UserCreateEditDto> {
    @Override
    public boolean isValid(UserCreateEditDto userCreateEditDto, ConstraintValidatorContext constraintValidatorContext) {
        return (LocalDate.now().getYear() - userCreateEditDto.getBirthday().getYear()) >= 18;
    }
}
