package by.kozlov.hibernate.starter.mapper;

import by.kozlov.hibernate.starter.dto.UserDto;
import by.kozlov.hibernate.starter.entity.User;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;
public class UserMapper implements Mapper<User, UserDto> {

    @Override
    public UserDto mapFrom(User object) {
        return UserDto.builder()
                .id(object.getId())
                .name(object.getName())
                .birthday(object.getBirthday())
                .email(object.getEmail())
                .role(object.getRole())
                .gender(object.getGender())
                .build();
    }
}
