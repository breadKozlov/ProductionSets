package by.kozlov.hibernate.starter.dto;

import by.kozlov.hibernate.starter.entity.Gender;
import by.kozlov.hibernate.starter.entity.Role;
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