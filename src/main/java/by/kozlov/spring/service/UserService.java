package by.kozlov.spring.service;

import by.kozlov.spring.database.entity.Role;
import by.kozlov.spring.database.repository.WorkerRepository;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserCreateEditMapper userCreateEditMapper;
    private final UserReadMapper userReadMapper;
    private final WorkerRepository workerRepository;
    public Optional<UserReadDto> login(LoginDto loginDto) {
        try {
            Optional<UserReadDto> user;
            user = userRepository.findByEmailAndPassword(loginDto.email(),loginDto.password())
                    .map(userReadMapper::map);
            return user;
        } catch (RuntimeException exception) {
            return Optional.empty();
        }
    }

    public UserReadDto create(UserCreateEditDto userDto) {
        return Optional.of(userDto)
                .map(userCreateEditMapper::map)
                .map(userRepository::save)
                .map(userReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public boolean delete(Integer id) {
        return userRepository.findById(id)
                .map(entity -> {
                    userRepository.delete(entity);
                    userRepository.flush();
                    return true;
                }).orElse(false);
    }
}
