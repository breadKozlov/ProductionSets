package by.kozlov.hibernate.starter.dao;

import by.kozlov.hibernate.starter.entity.Material;

import javax.persistence.EntityManager;

public class MaterialRepository extends BaseRepository<Integer,Material>{
    public MaterialRepository(EntityManager entityManager) {
        super(Material.class, entityManager);
    }
}
