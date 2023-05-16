package by.kozlov.hibernate.starter.dao;

import by.kozlov.hibernate.starter.entity.User;
import by.kozlov.hibernate.starter.exception.DaoException;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import java.util.Optional;

public class UserRepository extends BaseRepository<Integer, User>{
    public UserRepository(EntityManager entityManager) {
        super(User.class, entityManager);
    }

    private static final String FIND_ALL_HQL = """
            FROM User U
            """;

    private static final String FIND_BY_EMAIL_AND_PASS_HQL = FIND_ALL_HQL + """
             WHERE U.email = :email AND U.password = :password
            """;

    public Optional<User> findByEmailAndPassword(String email, String password) {

            return Optional.ofNullable(getEntityManager().createQuery(FIND_BY_EMAIL_AND_PASS_HQL, User.class)
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .getSingleResult());
    }
}
