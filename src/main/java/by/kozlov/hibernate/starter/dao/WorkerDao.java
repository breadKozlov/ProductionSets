package by.kozlov.hibernate.starter.dao;

import by.kozlov.hibernate.starter.entity.Worker;
import by.kozlov.hibernate.starter.exception.DaoException;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class WorkerDao implements DaoHibernate<Integer,Worker>{

    private static final WorkerDao INSTANCE = new WorkerDao();

    private static final String FIND_ALL_HQL = """
            FROM Worker W JOIN FETCH W.brigade
            """;

    private static final String FIND_BY_ID_HQL = FIND_ALL_HQL + """
             WHERE W.id = :id
            """;

    private static final String FIND_BY_EMAIL_HQL = FIND_ALL_HQL + """
             WHERE W.email = :email
            """;

    public Optional<Worker> findByEmail(Session session,String email) {

        try {
            return session.createQuery(FIND_BY_EMAIL_HQL,Worker.class)
                    .setParameter("email",email).list().stream().findFirst();
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }
    private WorkerDao() {}

    public static WorkerDao getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean update(Session session, Worker worker) {
        return false;
    }

    @Override
    public Optional<Worker> findById(Session session, Integer id) {
        try {
            return session.createQuery(FIND_BY_ID_HQL, Worker.class).setParameter("id",id)
                    .list().stream().findFirst();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Worker> findAll(Session session) {
        try {
            return session.createQuery(FIND_ALL_HQL, Worker.class).list();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean delete(Session session, Integer id) {
        return false;
    }

    @Override
    public Worker save(Session session, Worker worker) {
        return null;
    }
}
