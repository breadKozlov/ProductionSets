package by.kozlov.hibernate.starter.dto;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
