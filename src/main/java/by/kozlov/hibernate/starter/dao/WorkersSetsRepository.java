package by.kozlov.hibernate.starter.dao;

import by.kozlov.hibernate.starter.entity.WorkersSets;
import by.kozlov.hibernate.starter.exception.DaoException;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class WorkersSetsRepository extends BaseRepository<Integer, WorkersSets> {
    public WorkersSetsRepository(EntityManager entityManager) {
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

    private static final String FIND_ALL_RELEASED_SETS = """
            SELECT W.set.nameOfSet as name,W.requirement,(SELECT sum(P.madeSets)
             FROM Production P WHERE P.set.nameOfSet = W.set.nameOfSet AND P.worker.id = W.worker.id) FROM WorkersSets W
             where W.worker.id = :id
             ORDER BY name asc
            """;
    public List<Object[]> findAllProdSetsById(Integer id) {

            return getEntityManager().createQuery(FIND_ALL_RELEASED_SETS,Object[].class).setParameter("id",id).getResultList();
    }

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
