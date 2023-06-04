package by.kozlov.spring.dto;

import by.kozlov.spring.entity.Gender;
import by.kozlov.spring.entity.Role;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class UserDto {
    Integer id;
    String name;
    LocalDate birthday;
    String email;
    Role role;
    Gender gender;
}
