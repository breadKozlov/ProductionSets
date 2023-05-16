package by.kozlov.hibernate.starter.dao;

import by.kozlov.hibernate.starter.entity.Production;

import javax.persistence.EntityManager;

public class ProductionRepository extends BaseRepository<Integer, Production> {
    public ProductionRepository(EntityManager entityManager) {
        super(Production.class, entityManager);
    }
}
