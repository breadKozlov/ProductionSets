package by.kozlov.spring.dto;

import by.kozlov.spring.database.entity.Gender;
import by.kozlov.spring.database.entity.Role;
import by.kozlov.spring.validation.UserAge;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Value
@Builder
@UserAge
public class UserCreateEditDto {
    @NotEmpty(message = "Login is not be empty")
    String name;
    @NotNull
    LocalDate birthday;
    @NotEmpty(message = "Email is not be empty")
    @Email
    String email;
    @NotEmpty(message = "Password is not be empty")
    String password;
    @NotNull
    Role role;
    @NotNull(message = "Gender is not be empty")
    Gender gender;
    MultipartFile image;
}
