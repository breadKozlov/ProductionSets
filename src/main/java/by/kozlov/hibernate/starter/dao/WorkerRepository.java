package by.kozlov.hibernate.starter.dao;

import by.kozlov.hibernate.starter.entity.Worker;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class WorkerRepository extends BaseRepository<Integer, Worker>{

    public WorkerRepository(EntityManager entityManager) {
        super(Worker.class,entityManager);
    }

    private static final String FIND_ALL_HQL = """
            FROM Worker W JOIN FETCH W.brigade
            """;
    private static final String FIND_BY_EMAIL_HQL = FIND_ALL_HQL + """
             WHERE W.email = :email
            """;

    private static final String FIND_BY_ID_HQL = FIND_ALL_HQL + """
             WHERE W.id = :id
            """;

    public Optional<Worker> findByEmail(String email) {

            return Optional.ofNullable(getEntityManager().createQuery(FIND_BY_EMAIL_HQL, Worker.class)
                    .setParameter("email", email).getSingleResult());
    }

    @Override
    public List<Worker> findAll() {
            return getEntityManager().createQuery(FIND_ALL_HQL, Worker.class).getResultList();
    }

    @Override
    public Optional<Worker> findById(Integer id) {
            return Optional.ofNullable(getEntityManager().createQuery(FIND_BY_ID_HQL, Worker.class).setParameter("id", id)
                    .getSingleResult());
    }
}
