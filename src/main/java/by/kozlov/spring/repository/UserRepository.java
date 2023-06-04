package by.kozlov.spring.repository;

import by.kozlov.spring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    String FIND_ALL_HQL = """
            FROM User U
            """;

    String FIND_BY_EMAIL_AND_PASS_HQL = FIND_ALL_HQL + """
             WHERE U.email = :email AND U.password = :password
            """;

    @Query(FIND_BY_EMAIL_AND_PASS_HQL)
    Optional<User> findByEmailAndPassword (@Param("email") String email, @Param("password") String password);

}
