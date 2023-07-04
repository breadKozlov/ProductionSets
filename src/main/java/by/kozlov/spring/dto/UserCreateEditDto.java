package by.kozlov.spring.dto;

import by.kozlov.spring.database.entity.Gender;
import by.kozlov.spring.database.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class UserCreateEditDto {
    @NotEmpty
    String name;
    @NotNull
    LocalDate birthday;
    @NotEmpty
    @Email
    String email;
    @NotEmpty
    String password;
    @NotNull
    Role role;
    @NotNull
    Gender gender;
}
