package by.kozlov.hibernate.starter.dao;

import by.kozlov.hibernate.starter.entity.MaterialsProduction;
import by.kozlov.hibernate.starter.exception.DaoException;
import by.kozlov.hibernate.starter.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class MaterialsProductionDao implements DaoHibernate<Integer,MaterialsProduction> {

    private final SessionFactory sessionFactory = HibernateUtil.getConfig().buildSessionFactory();
    private static final MaterialsProductionDao INSTANCE = new MaterialsProductionDao();

    private static final String FIND_ALL_HQL = """
            FROM MaterialsProduction M JOIN FETCH M.material JOIN FETCH M.brigade
            """;

    private static final String FIND_BY_ID_HQL = FIND_ALL_HQL + """
             WHERE M.id = :id
            """;

    private static final String DELETE_HQL = """
            DELETE MaterialsProduction M WHERE M.id = :id
            """;

    private static final String FIND_BY_ID_BRIGADE_HQL = FIND_ALL_HQL + """
             WHERE M.brigade.id = :id
            """;

    private static final String UPDATE_HQL = """
            UPDATE MaterialsProduction M SET
            M.material = :material,
            M.brigade = :brigade,
            M.quantity = :quantity,
            M.dateOfProduction = :date
            WHERE M.id = :id
            """;

    private static final String FIND_SUM_REQ_MAT = """
            SELECT M.material.nameOfMaterial as name_1,sum(M.quantity)
            from MaterialsProduction M
            group by name_1
            """;

    public List<MaterialsProduction> findAllByBrigadeId(Session session, Integer brigadeId) {
        try {
            return session.createQuery(FIND_BY_ID_BRIGADE_HQL, MaterialsProduction.class)
                    .setParameter("id",brigadeId).list();
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }

    public List<Object[]> findSumAllRelMat(Session session) {
        try {
            return session.createQuery(FIND_SUM_REQ_MAT,Object[].class).list();
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }



    private MaterialsProductionDao() {}

    public static MaterialsProductionDao getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean update(Session session, MaterialsProduction materialsProduction) {
        try {
            Query query = session.createQuery(UPDATE_HQL);
            query.setParameter("material",materialsProduction.getMaterial());
            query.setParameter("brigade",materialsProduction.getBrigade());
            query.setParameter("quantity",materialsProduction.getQuantity());
            query.setParameter("date",materialsProduction.getDateOfProduction());
            query.setParameter("id",materialsProduction.getId());

            return query.executeUpdate() > 0;
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

@Override
public Optional<MaterialsProduction> findById(Session session, Integer id) {
    try {
        return session.createQuery(FIND_BY_ID_HQL,MaterialsProduction.class).setParameter("id",id)
                .list().stream().findFirst();
    } catch (Exception e) {
        throw new DaoException(e);
    }
}

@Override
public List<MaterialsProduction> findAll(Session session) {
    try {
        return session.createQuery(FIND_ALL_HQL, MaterialsProduction.class).list();
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
public MaterialsProduction save(Session session, MaterialsProduction materialsProduction) {
    try {
        session.save(materialsProduction);
        return materialsProduction;
    } catch (Exception e) {
        throw new DaoException(e);
    }
}
}
