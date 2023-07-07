package by.kozlov.spring.service;

import by.kozlov.spring.database.repository.UserRepository;
import by.kozlov.spring.dto.LoginDto;
import by.kozlov.spring.dto.UserCreateEditDto;
import by.kozlov.spring.dto.UserReadDto;
import by.kozlov.spring.mapper.UserCreateEditMapper;
import by.kozlov.spring.mapper.UserReadMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import by.kozlov.spring.database.entity.User;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final UserCreateEditMapper userCreateEditMapper;
    private final UserReadMapper userReadMapper;
    private final ImageService imageService;

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

    public boolean checkEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Transactional
    public UserReadDto create(UserCreateEditDto userDto) {
        return Optional.of(userDto)
                .map(dto -> {
                    uploadImage(dto.getImage());
                    return userCreateEditMapper.map(dto);
                })
                .map(userRepository::saveAndFlush)
                .map(userReadMapper::map)
                .orElseThrow();
    }

    @SneakyThrows
    private void uploadImage(MultipartFile image) {
        if(!image.isEmpty()) {
            imageService.upload(image.getOriginalFilename(), image.getInputStream());
        }
    }

    public Optional<byte[]> findAvatar(Integer id) {
        return userRepository.findById(id)
                .map(User::getImage)
                .filter(StringUtils::hasText)
                .flatMap(imageService::get);
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
