package by.kozlov.spring.dto;

import by.kozlov.spring.database.entity.Gender;
import by.kozlov.spring.database.entity.Role;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class UserReadDto {
    Integer id;
    String name;
    LocalDate birthday;
    String email;
    String image;
    Role role;
    Gender gender;
}
