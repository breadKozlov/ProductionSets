package by.kozlov.spring.mapper;

import by.kozlov.spring.database.entity.User;
import by.kozlov.spring.dto.UserCreateEditDto;
import org.springframework.stereotype.Component;

@Component
public class UserCreateEditMapper implements Mapper<UserCreateEditDto,User> {
    @Override
    public User map(UserCreateEditDto object) {
        User user = new User();
        copy(object,user);
        return user;
    }

    @Override
    public User map(UserCreateEditDto fromObject, User toObject) {
        copy(fromObject,toObject);
        return toObject;
    }

    @Override
    public void copy(UserCreateEditDto fromObject, User toObject) {
        toObject.setName(fromObject.getName());
        toObject.setBirthday(fromObject.getBirthday());
        toObject.setEmail(fromObject.getEmail());
        toObject.setPassword(fromObject.getPassword());
        toObject.setRole(fromObject.getRole());
        toObject.setGender(fromObject.getGender());
    }
}
