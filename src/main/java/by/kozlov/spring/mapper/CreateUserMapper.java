package by.kozlov.spring.mapper;


import by.kozlov.spring.dto.CreateUserDto;
import by.kozlov.spring.entity.Gender;
import by.kozlov.spring.entity.Role;
import by.kozlov.spring.entity.User;
import by.kozlov.spring.utils.LocalDateFormatter;
import org.springframework.stereotype.Component;

@Component
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