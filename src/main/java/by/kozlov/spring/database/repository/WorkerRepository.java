package by.kozlov.spring.database.repository;

import by.kozlov.spring.database.entity.Worker;
import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkerRepository extends JpaRepository<Worker,Integer> {

    String FIND_ALL_HQL = """
            FROM Worker W JOIN FETCH W.brigade
            """;
    String FIND_BY_EMAIL_HQL = FIND_ALL_HQL + """
             WHERE W.email = :email
            """;

    String FIND_BY_ID_HQL = FIND_ALL_HQL + """
             WHERE W.id = :id
            """;

    @Query(FIND_BY_EMAIL_HQL)
    Optional<Worker> findByEmail(@Param("email") @NotNull String email);

    @Override
    @Query(FIND_ALL_HQL)
    @Nonnull
    List<Worker> findAll();

    @Override
    @Query(FIND_BY_ID_HQL)
    @Nonnull
    Optional<Worker> findById(@Param("id") @Nonnull Integer id);
}

