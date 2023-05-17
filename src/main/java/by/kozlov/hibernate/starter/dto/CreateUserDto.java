package by.kozlov.hibernate.starter.dto;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Value
@Builder
public class CreateUserDto {
    String name;
    @NotNull
    @NotEmpty
    String birthday;
    @NotNull
    String email;
    String password;
    String role;
    String gender;
}
