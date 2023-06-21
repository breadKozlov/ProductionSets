package by.kozlov.spring.dto;

import by.kozlov.spring.database.entity.Gender;
import by.kozlov.spring.database.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class UserCreateEditDto {
    @NotEmpty
    String name;
    @NotEmpty
    LocalDate birthday;
    @NotEmpty
    @Email
    String email;
    @NotEmpty
    String password;
    @NotEmpty
    Role role;
    @NotEmpty
    Gender gender;
}
