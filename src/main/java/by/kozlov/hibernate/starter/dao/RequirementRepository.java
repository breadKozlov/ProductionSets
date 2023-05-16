package by.kozlov.hibernate.starter.dao;

import by.kozlov.hibernate.starter.entity.Requirement;
import by.kozlov.hibernate.starter.exception.DaoException;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class RequirementRepository extends BaseRepository<Integer, Requirement> {
    public RequirementRepository(EntityManager entityManager) {
        super(Requirement.class, entityManager);
    }

    private static final String FIND_ALL_HQL = """
            FROM Requirement R JOIN FETCH R.set JOIN FETCH R.material
            """;

    private static final String FIND_BY_ID_HQL = FIND_ALL_HQL + """
             WHERE R.id = :id
            """;

    private static final String FIND_ALL_BY_ID_SET_HQL = FIND_ALL_HQL + """
             WHERE R.set.id = :id
            """;

    private static final String FIND_SUM_REQ_MAT = """
            SELECT R.material.nameOfMaterial as name,sum(R.totalSets * R.unitCost),(select sum(M.quantity)
            from MaterialsProduction M where M.material.nameOfMaterial = R.material.nameOfMaterial)
            from Requirement R group by name order by name asc
            """;

    public List<Requirement> findAllBySetId(Integer id) {

            return getEntityManager().createQuery(FIND_ALL_BY_ID_SET_HQL, Requirement.class)
                    .setParameter("id",id).getResultList();
    }

    public List<Object[]> findSumAllReqMat() {

            return getEntityManager().createQuery(FIND_SUM_REQ_MAT,Object[].class).getResultList();
    }

    @Override
    public Optional<Requirement> findById(Integer id) {
            return Optional.ofNullable(getEntityManager().createQuery(FIND_BY_ID_HQL, Requirement.class).setParameter("id", id)
                    .getSingleResult());

    }

    @Override
    public List<Requirement> findAll() {
            return getEntityManager().createQuery(FIND_ALL_HQL, Requirement.class).getResultList();

    }
}
