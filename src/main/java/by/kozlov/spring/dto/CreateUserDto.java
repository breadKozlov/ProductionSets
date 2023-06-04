package by.kozlov.spring.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateUserDto {
    @NotEmpty
    String name;
    @NotEmpty
    String birthday;
    @NotEmpty
    @Email
    String email;
    @NotEmpty
    String password;
    @NotEmpty
    String role;
    @NotEmpty
    String gender;
}
