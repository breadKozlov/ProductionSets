package by.kozlov.hibernate.starter.dao;

import by.kozlov.hibernate.starter.entity.Set;
import by.kozlov.hibernate.starter.exception.DaoException;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class SetDao implements DaoHibernate<Integer,Set>{

    private static final SetDao INSTANCE = new SetDao();

    private static final String FIND_ALL_HQL = """
            FROM Set S
            """;

    private static final String FIND_BY_ID_HQL = FIND_ALL_HQL + """
             WHERE S.id = :id
            """;

    private SetDao() {}

    public static SetDao getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean update(Session session, Set set) {
        return false;
    }

    @Override
    public Optional<Set> findById(Session session, Integer id) {
        try {
            return session.createQuery(FIND_BY_ID_HQL,Set.class).setParameter("id",id)
                    .list().stream().findFirst();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Set> findAll(Session session) {
        try {
            return session.createQuery(FIND_ALL_HQL, Set.class).list();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean delete(Session session, Integer id) {
        return false;
    }

    @Override
    public Set save(Session session, Set set) {
        return null;
    }
}
