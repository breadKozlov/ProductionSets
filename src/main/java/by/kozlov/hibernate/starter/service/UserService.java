package by.kozlov.hibernate.starter.service;

import by.kozlov.hibernate.starter.dto.WorkerDto;
import by.kozlov.hibernate.starter.exception.ValidationException;
import by.kozlov.hibernate.starter.utils.HibernateUtil;
import by.kozlov.hibernate.starter.validator.CreateUserValidator;
import by.kozlov.hibernate.starter.dao.UserDao;
import by.kozlov.hibernate.starter.dto.CreateUserDto;
import by.kozlov.hibernate.starter.dto.UserDto;
import by.kozlov.hibernate.starter.mapper.CreateUserMapper;
import by.kozlov.hibernate.starter.mapper.UserMapper;
import lombok.NoArgsConstructor;
import org.hibernate.SessionFactory;

import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserService {

    private final SessionFactory sessionFactory = HibernateUtil.getConfig().buildSessionFactory();

    private static final UserService INSTANCE = new UserService();

    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
    private final UserDao userDao = UserDao.getInstance();
    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();

    private final UserMapper userMapper = UserMapper.getInstance();

    public Optional<UserDto> login(String email, String password) {
        try (var session = sessionFactory.openSession()) {
            Optional<UserDto> user;
            session.beginTransaction();
            user = userDao.findByEmailAndPassword(session,email,password)
                    .map(userMapper::mapFrom);
            session.getTransaction().commit();
            return user;
        }
    }

    public Integer create(CreateUserDto userDto) {
        try (var session = sessionFactory.openSession()) {
            var validationResult = createUserValidator.isValid(userDto);
            if (!validationResult.isValid()) {
                throw new ValidationException(validationResult.getErrors());
            }
            var productionEntity = createUserMapper.mapFrom(userDto);
            session.beginTransaction();
            productionEntity = userDao.save(session,productionEntity);
            session.getTransaction().commit();
            return productionEntity.getId();
        }
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
