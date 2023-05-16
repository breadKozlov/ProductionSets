package by.kozlov.hibernate.starter.dao;

import by.kozlov.hibernate.starter.entity.Brigade;

import javax.persistence.EntityManager;

public class BrigadeRepository extends BaseRepository<Integer, Brigade> {
    public BrigadeRepository(EntityManager entityManager) {
        super(Brigade.class, entityManager);
    }
}
