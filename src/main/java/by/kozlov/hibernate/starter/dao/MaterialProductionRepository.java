package by.kozlov.hibernate.starter.dao;

import by.kozlov.hibernate.starter.entity.MaterialsProduction;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class MaterialProductionRepository extends BaseRepository<Integer, MaterialsProduction> {
    public MaterialProductionRepository(EntityManager entityManager) {
        super(MaterialsProduction.class, entityManager);
    }

    private static final String FIND_ALL_HQL = """
            FROM MaterialsProduction M JOIN FETCH M.material JOIN FETCH M.brigade
            """;

    private static final String FIND_BY_ID_HQL = FIND_ALL_HQL + """
             WHERE M.id = :id
            """;

    private static final String FIND_BY_ID_BRIGADE_HQL = FIND_ALL_HQL + """
             WHERE M.brigade.id = :id
            """;

    public List<MaterialsProduction> findAllByBrigadeId(Integer brigadeId) {
        return getEntityManager().createQuery(FIND_BY_ID_BRIGADE_HQL, MaterialsProduction.class)
                    .setParameter("id",brigadeId).getResultList();
    }

    @Override
    public Optional<MaterialsProduction> findById(Integer id) {
            return Optional.ofNullable(getEntityManager().createQuery(FIND_BY_ID_HQL, MaterialsProduction.class).setParameter("id", id)
                    .getSingleResult());
    }

    @Override
    public List<MaterialsProduction> findAll() {
            return getEntityManager().createQuery(FIND_ALL_HQL, MaterialsProduction.class).getResultList();
    }
}
