package by.kozlov.hibernate.starter.dao;

import by.kozlov.hibernate.starter.entity.WorkersSets;
import by.kozlov.hibernate.starter.exception.DaoException;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class WorkerSetRepository extends BaseRepository<Integer, WorkersSets> {
    public WorkerSetRepository(EntityManager entityManager) {
        super(WorkersSets.class, entityManager);
    }

    private static final String FIND_ALL_HQL = """
            FROM WorkersSets W JOIN FETCH W.set JOIN FETCH W.worker JOIN FETCH W.worker.brigade
            """;

    private static final String FIND_BY_ID_WORKER_HQL = FIND_ALL_HQL + """
             WHERE W.worker.id = :id
            """;

    private static final String FIND_BY_ID_HQL = FIND_ALL_HQL + """
             WHERE W.id = :id
            """;

    public List<WorkersSets> findAllByWorkerId(Integer idWorker) {
            return getEntityManager().createQuery(FIND_BY_ID_WORKER_HQL,WorkersSets.class)
                    .setParameter("id",idWorker).getResultList();
    }

    @Override
    public Optional<WorkersSets> findById(Integer id) {
            return Optional.ofNullable(getEntityManager().createQuery(FIND_BY_ID_HQL, WorkersSets.class).setParameter("id", id)
                    .getSingleResult());
    }

    @Override
    public List<WorkersSets> findAll() {
            return getEntityManager().createQuery(FIND_ALL_HQL, WorkersSets.class).getResultList();
    }
}
