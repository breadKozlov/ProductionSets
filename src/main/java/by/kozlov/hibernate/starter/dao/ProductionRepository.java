package by.kozlov.hibernate.starter.dao;

import by.kozlov.hibernate.starter.entity.Production;
import by.kozlov.hibernate.starter.exception.DaoException;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class ProductionRepository extends BaseRepository<Integer, Production> {
    public ProductionRepository(EntityManager entityManager) {
        super(Production.class, entityManager);
    }

    private static final String FIND_ALL_HQL = """
            FROM Production P JOIN FETCH P.set JOIN FETCH P.worker JOIN FETCH P.worker.brigade
            """;

    private static final String FIND_BY_ID_HQL = FIND_ALL_HQL + """
             WHERE P.id = :id
            """;

    private static final String FIND_BY_ID_WORKER_HQL = FIND_ALL_HQL + """
             WHERE P.worker.id = :id
            """;

    private static final String FIND_SUM_ALL_SETS = """
            SELECT R.set.nameOfSet as name,avg(R.totalSets),(select sum(P.madeSets)
            from Production P where P.set.nameOfSet = R.set.nameOfSet)
            from Requirement R group by name order by name asc
            """;

    public List<Object[]> findSumAllProdSets() {

            return getEntityManager().createQuery(FIND_SUM_ALL_SETS,Object[].class).getResultList();
    }

    public List<Production> findAllByWorkerId(Session session, Integer idWorker) {

            return getEntityManager().createQuery(FIND_BY_ID_WORKER_HQL,Production.class)
                    .setParameter("id",idWorker).getResultList();
    }

    @Override
    public Optional<Production> findById(Integer id) {
            return Optional.ofNullable(getEntityManager().createQuery(FIND_BY_ID_HQL, Production.class).setParameter("id", id)
                    .getSingleResult());
    }

    @Override
    public List<Production> findAll() {
            return getEntityManager().createQuery(FIND_ALL_HQL, Production.class).getResultList();
    }


}
