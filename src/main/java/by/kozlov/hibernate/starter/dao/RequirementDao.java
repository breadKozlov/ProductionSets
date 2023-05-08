package by.kozlov.hibernate.starter.dao;

import by.kozlov.hibernate.starter.entity.Requirement;
import by.kozlov.hibernate.starter.exception.DaoException;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class RequirementDao implements DaoHibernate<Integer,Requirement> {

    private static final RequirementDao INSTANCE = new RequirementDao();

    private static final String FIND_ALL_HQL = """
            FROM Requirement R JOIN FETCH R.set JOIN FETCH R.material
            """;

    private static final String FIND_BY_ID_HQL = FIND_ALL_HQL + """
             WHERE R.id = :id
            """;

    private static final String DELETE_HQL = """
            DELETE Requirement R WHERE R.id = :id
            """;

    private static final String FIND_ALL_BY_ID_SET_HQL = FIND_ALL_HQL + """
             WHERE R.set.id = :id
            """;

    private static final String UPDATE_HQL = """
            UPDATE Requirement R SET
            R.set = :set,
            R.material = :material,
            R.unitCost = :unitCost,
            R.totalSets = :totalSets
            WHERE R.id = :id
            """;

    private static final String FIND_SUM_REQ_MAT = """
            SELECT R.material.nameOfMaterial, sum(R.unitCost * R.totalSets) FROM Requirement R
            GROUP BY R.material.nameOfMaterial
            """;

    public List<Requirement> findAllBySetId(Session session, Integer id) {
        try {
            return session.createQuery(FIND_ALL_BY_ID_SET_HQL, Requirement.class)
                    .setParameter("id",id).list();
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }

    public List<Object[]> findSumAllReqMat(Session session) {
        try {
            return session.createQuery(FIND_SUM_REQ_MAT,Object[].class).list();
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }

    private RequirementDao() {}

    public static RequirementDao getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean update(Session session, Requirement requirement) {
        try {
            Query query = session.createQuery(UPDATE_HQL);
            query.setParameter("set",requirement.getSet());
            query.setParameter("material",requirement.getMaterial());
            query.setParameter("unitCost",requirement.getUnitCost());
            query.setParameter("totalSets",requirement.getTotalSets());
            query.setParameter("id",requirement.getId());

            return query.executeUpdate() > 0;
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Requirement> findById(Session session, Integer id) {
        try {
            return session.createQuery(FIND_BY_ID_HQL,Requirement.class).setParameter("id",id)
                    .list().stream().findFirst();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Requirement> findAll(Session session) {
        try {
            return session.createQuery(FIND_ALL_HQL, Requirement.class).list();
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
    public Requirement save(Session session, Requirement requirement) {
        try {
            session.save(requirement);
            return requirement;
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}
