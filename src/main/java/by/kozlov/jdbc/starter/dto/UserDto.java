package by.kozlov.jdbc.starter.dto;

import by.kozlov.jdbc.starter.entity.Gender;
import by.kozlov.jdbc.starter.entity.Role;
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