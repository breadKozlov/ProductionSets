package by.kozlov.hibernate.starter.mapper;

import by.kozlov.hibernate.starter.dto.CreateUserDto;
import by.kozlov.hibernate.starter.entity.Gender;
import by.kozlov.hibernate.starter.entity.Role;
import by.kozlov.hibernate.starter.entity.User;
import by.kozlov.hibernate.starter.utils.LocalDateFormatter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;


public class CreateUserMapper implements Mapper<CreateUserDto, User> {
    @Override
    public User mapFrom(CreateUserDto object) {
        return User.builder()
                .name(object.getName())
                .birthday(LocalDateFormatter.format(object.getBirthday()))
                .email(object.getEmail())
                .password(object.getPassword())
                .gender(Gender.valueOf(object.getGender()))
                .role(Role.valueOf(object.getRole()))
                .build();
    }
}
