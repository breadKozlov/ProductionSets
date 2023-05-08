package by.kozlov.hibernate.starter.dao;

import by.kozlov.hibernate.starter.entity.User;
import by.kozlov.hibernate.starter.exception.DaoException;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserDao implements DaoHibernate<Integer,User> {

    private static final UserDao INSTANCE = new UserDao();

    private static final String FIND_ALL_HQL = """
            FROM User U
            """;

    private static final String FIND_BY_ID_HQL = FIND_ALL_HQL + """
             WHERE U.id = :id
            """;

    private static final String FIND_BY_EMAIL_AND_PASS_HQL = FIND_ALL_HQL + """
             WHERE U.email = :email AND U.password = :password
            """;

    @SneakyThrows
    public Optional<User> findByEmailAndPassword(Session session,String email, String password) {
        try {
            return session.createQuery(FIND_BY_EMAIL_AND_PASS_HQL,User.class)
                    .setParameter("email",email)
                    .setParameter("password",password)
                    .list().stream().findFirst();
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean update(Session session, User user) {
        return false;
    }

    @Override
    public Optional<User> findById(Session session, Integer id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll(Session session) {
        return null;
    }

    @Override
    public boolean delete(Session session, Integer id) {
        return false;
    }

    @Override
    public User save(Session session, User user) {
        try {
            session.save(user);
            return user;
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}