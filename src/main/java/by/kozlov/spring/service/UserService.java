package by.kozlov.spring.service;

import by.kozlov.spring.dto.LoginDto;
import by.kozlov.spring.dto.UserCreateEditDto;
import by.kozlov.spring.dto.UserReadDto;
import by.kozlov.spring.mapper.UserCreateEditMapper;
import by.kozlov.spring.database.repository.UserRepository;
import by.kozlov.spring.mapper.UserReadMapper;
import jakarta.persistence.NoResultException;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserCreateEditMapper userCreateEditMapper;
    private final UserReadMapper userReadMapper;
    public Optional<UserReadDto> login(LoginDto loginDto) {
        try {
            Optional<UserReadDto> user;
            user = userRepository.findByEmailAndPassword(loginDto.email(),loginDto.password())
                    .map(userReadMapper::map);
            return user;
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    public UserReadDto create(UserCreateEditDto userDto) {
        try (var validationFactory = Validation.buildDefaultValidatorFactory()) {

            var validator = validationFactory.getValidator();
            var validationResult = validator.validate(userDto);
            if (!validationResult.isEmpty()) {
                throw new ConstraintViolationException(validationResult);
            }
            return Optional.of(userDto)
                    .map(userCreateEditMapper::map)
                    .map(userRepository::save)
                    .map(userReadMapper::map)
                    .orElseThrow();
        }
    }
}
