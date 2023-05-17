package by.kozlov.hibernate.starter.service;

import by.kozlov.hibernate.starter.dao.*;
import by.kozlov.hibernate.starter.dto.WorkerDto;
import by.kozlov.hibernate.starter.exception.ValidationException;
import by.kozlov.hibernate.starter.mapper.*;
import by.kozlov.hibernate.starter.utils.HibernateUtil;
import by.kozlov.hibernate.starter.validator.CreateUserValidator;
import by.kozlov.hibernate.starter.dto.CreateUserDto;
import by.kozlov.hibernate.starter.dto.UserDto;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.lang.reflect.Proxy;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

public class UserService {

    private final SessionFactory sessionFactory;

    private static final UserService INSTANCE = new UserService();

    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
    private final UserRepository userRepository;
    private final CreateUserMapper createUserMapper = new CreateUserMapper();
    private final UserMapper userMapper = new UserMapper();

    private final Session session;

    private UserService() {
        sessionFactory = HibernateUtil.getConfig().buildSessionFactory();
        session = (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(),
                new Class[]{Session.class},
                (proxy, method, args1) -> method.invoke(sessionFactory.getCurrentSession(), args1));
        userRepository = new UserRepository(session);
    }

    public Optional<UserDto> login(String email, String password) {
        try (session) {
            Optional<UserDto> user;
            session.beginTransaction();
            user = userRepository.findByEmailAndPassword(email,password)
                    .map(userMapper::mapFrom);
            session.getTransaction().commit();
            return user;
        }
    }

    public Integer create(CreateUserDto userDto) {
        try (session) {
            var validationResult = createUserValidator.isValid(userDto);
            if (!validationResult.isValid()) {
                throw new ValidationException(validationResult.getErrors());
            }
            session.beginTransaction();
            var productionEntity = createUserMapper.mapFrom(userDto);
            productionEntity = userRepository.save(productionEntity);
            session.getTransaction().commit();
            return productionEntity.getId();
        }
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
