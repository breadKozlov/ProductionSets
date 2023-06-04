package by.kozlov.spring.service;

import by.kozlov.spring.dto.CreateUserDto;
import by.kozlov.spring.dto.UserDto;
import by.kozlov.spring.mapper.CreateUserMapper;
import by.kozlov.spring.mapper.UserMapper;
import by.kozlov.spring.repository.UserRepository;
import jakarta.persistence.NoResultException;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final CreateUserMapper createUserMapper;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository,
                       CreateUserMapper createUserMapper,
                       UserMapper userMapper) {
        this.userRepository = userRepository;
        this.createUserMapper = createUserMapper;
        this.userMapper = userMapper;
    }

    public Optional<UserDto> login(String email, String password) {
        try {
            Optional<UserDto> user;
            user = userRepository.findByEmailAndPassword(email,password)
                    .map(userMapper::mapFrom);
            return user;
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    public Integer create(CreateUserDto userDto) {
        try (var validationFactory = Validation.buildDefaultValidatorFactory()) {

            var validator = validationFactory.getValidator();
            var validationResult = validator.validate(userDto);
            if (!validationResult.isEmpty()) {
                throw new ConstraintViolationException(validationResult);
            }
            var productionEntity = createUserMapper.mapFrom(userDto);
            productionEntity = userRepository.saveAndFlush(productionEntity);
            return productionEntity.getId();
        }
    }
}
