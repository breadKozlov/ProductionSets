package by.kozlov.hibernate.starter.dao;

import by.kozlov.hibernate.starter.entity.WorkersSets;
import by.kozlov.hibernate.starter.exception.DaoException;
import by.kozlov.hibernate.starter.utils.HibernateUtil;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class WorkersSetsDao implements DaoHibernate<Integer,WorkersSets> {

    private final SessionFactory sessionFactory = HibernateUtil.getConfig().buildSessionFactory();

    private static final WorkersSetsDao INSTANCE = new WorkersSetsDao();

    private static final String FIND_ALL_HQL = """
            FROM WorkersSets W JOIN FETCH W.set JOIN FETCH W.worker JOIN FETCH W.worker.brigade
            """;

    private static final String DELETE_HQL = """
            DELETE WorkersSets W WHERE W.id = :id
            """;

    private static final String FIND_BY_ID_WORKER_HQL = FIND_ALL_HQL + """
             WHERE W.worker.id = :id
            """;

    private static final String UPDATE_HQL = """
            UPDATE WorkersSets W SET
            W.worker = :worker,
            W.set = :set,
            W.requirement = :requirement
            WHERE W.id = :id
            """;

    public static WorkersSetsDao getInstance() {
        return INSTANCE;
    }

    public List<WorkersSets> findAllByWorkerId(Session session, Integer idWorker) {
        try {
            return session.createQuery(FIND_BY_ID_WORKER_HQL,WorkersSets.class)
                    .setParameter("id",idWorker).list();
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public boolean update(Session session, WorkersSets workersSets) {
        try {
            Query query = session.createQuery(UPDATE_HQL);
            query.setParameter("worker",workersSets.getWorker());
            query.setParameter("set",workersSets.getSet());
            query.setParameter("requirement",workersSets.getRequirement());
            query.setParameter("id",workersSets.getId());

            return query.executeUpdate() > 0;
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<WorkersSets> findById(Session session, Integer id) {
        return Optional.empty();
    }

    @Override
    public List<WorkersSets> findAll(Session session) {
        try {
            return session.createQuery(FIND_ALL_HQL, WorkersSets.class).list();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean delete(Session session, Integer id) {
        try {
            return session.createQuery(DELETE_HQL).setParameter("id",id)
                    .executeUpdate() > 0;
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public WorkersSets save(Session session, WorkersSets workersSets) {
        try {
            session.save(workersSets);
            return workersSets;
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}
