package by.kozlov.hibernate.starter.dao;

import by.kozlov.hibernate.starter.entity.ShiftHour;

import javax.persistence.EntityManager;

public class ShiftHourRepository extends BaseRepository<Integer, ShiftHour> {
    public ShiftHourRepository(EntityManager entityManager) {
        super(ShiftHour.class, entityManager);
    }
}
