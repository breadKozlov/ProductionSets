package by.kozlov.hibernate.starter.dao;

import by.kozlov.hibernate.starter.entity.Brigade;
import by.kozlov.hibernate.starter.exception.DaoException;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class BrigadeDao implements DaoHibernate<Integer,Brigade> {

    private static final BrigadeDao INSTANCE = new BrigadeDao();

    private static final String FIND_ALL_HQL = """
            FROM Brigade B
            """;

    private static final String FIND_BY_ID_HQL = FIND_ALL_HQL + """
             WHERE B.id = :id
            """;

    private BrigadeDao() {}

    public static BrigadeDao getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean update(Session session, Brigade brigade) {
        return false;
    }

    @Override
    public Optional<Brigade> findById(Session session, Integer id) {
        try {
            return session.createQuery(FIND_BY_ID_HQL, Brigade.class).setParameter("id",id)
                    .list().stream().findFirst();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Brigade> findAll(Session session) {
        try {
            return session.createQuery(FIND_ALL_HQL, Brigade.class).list();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean delete(Session session, Integer id) {
        return false;
    }

    @Override
    public Brigade save(Session session, Brigade brigade) {
        return null;
    }
}
