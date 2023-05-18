package by.kozlov.hibernate.starter.service;

import by.kozlov.hibernate.starter.dao.UserRepository;
import by.kozlov.hibernate.starter.dto.CreateUserDto;
import by.kozlov.hibernate.starter.dto.UserDto;
import by.kozlov.hibernate.starter.mapper.CreateUserMapper;
import by.kozlov.hibernate.starter.mapper.UserMapper;
import by.kozlov.hibernate.starter.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import java.util.Optional;

public class UserService {

    private static final UserService INSTANCE = new UserService();
    private final UserRepository userRepository;
    private final CreateUserMapper createUserMapper = new CreateUserMapper();
    private final UserMapper userMapper = new UserMapper();

    private final Session session;

    private UserService() {
        SessionFactory sessionFactory = HibernateUtil.getConfig().buildSessionFactory();
        session = HibernateUtil.getProxySession(sessionFactory);
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
        try (session;
             var validationFactory = Validation.buildDefaultValidatorFactory()) {

            var validator = validationFactory.getValidator();
            var validationResult = validator.validate(userDto);
            if (!validationResult.isEmpty()) {
                throw new ConstraintViolationException(validationResult);
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
