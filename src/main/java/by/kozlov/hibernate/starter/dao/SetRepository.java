package by.kozlov.hibernate.starter.dao;

import by.kozlov.hibernate.starter.entity.Set;

import javax.persistence.EntityManager;

public class SetRepository extends BaseRepository<Integer, Set>{
    public SetRepository(EntityManager entityManager) {
        super(Set.class, entityManager);
    }
}
