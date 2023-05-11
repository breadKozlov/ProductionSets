package by.kozlov.hibernate.starter.dao;

import by.kozlov.hibernate.starter.entity.Production;
import by.kozlov.hibernate.starter.exception.DaoException;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class ProductionDao implements DaoHibernate<Integer,Production> {

    private static final ProductionDao INSTANCE = new ProductionDao();

    private static final String FIND_ALL_HQL = """
            FROM Production P JOIN FETCH P.set JOIN FETCH P.worker JOIN FETCH P.worker.brigade
            """;

    private static final String FIND_BY_ID_HQL = FIND_ALL_HQL + """
             WHERE P.id = :id
            """;

    private static final String DELETE_HQL = """
            DELETE Production P WHERE P.id = :id
            """;

    private static final String FIND_BY_ID_WORKER_HQL = FIND_ALL_HQL + """
             WHERE P.worker.id = :id
            """;

    private static final String UPDATE_HQL = """
            UPDATE Production P SET
            P.worker = :worker,
            P.set = :set,
            P.madeSets = :madeSets,
            P.dateOfProduction = :date
            WHERE P.id = :id
            """;

    private static final String FIND_SUM_ALL_SETS = """
            SELECT R.set.nameOfSet as name,avg(R.totalSets),(select sum(P.madeSets)
            from Production P where P.set.nameOfSet = R.set.nameOfSet)
            from Requirement R group by name order by name asc
            """;

    public List<Object[]> findSumAllProdSets(Session session) {
        try {
            return session.createQuery(FIND_SUM_ALL_SETS,Object[].class).list();
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }

    public List<Production> findAllByWorkerId(Session session, Integer idWorker) {
        try {
            return session.createQuery(FIND_BY_ID_WORKER_HQL,Production.class)
                    .setParameter("id",idWorker).list();
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }
    private ProductionDao() {}

    public static ProductionDao getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean update(Session session, Production production) {
        try {
            Query query = session.createQuery(UPDATE_HQL);
            query.setParameter("worker",production.getWorker());
            query.setParameter("set",production.getSet());
            query.setParameter("madeSets",production.getMadeSets());
            query.setParameter("date",production.getDateOfProduction());
            query.setParameter("id",production.getId());

            return query.executeUpdate() > 0;
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Production> findById(Session session, Integer id) {
        try {
            return session.createQuery(FIND_BY_ID_HQL,Production.class).setParameter("id",id)
                    .list().stream().findFirst();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Production> findAll(Session session) {
        try {
            return session.createQuery(FIND_ALL_HQL, Production.class).list();
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
    public Production save(Session session, Production production) {
        try {
            session.save(production);
            return production;
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}
