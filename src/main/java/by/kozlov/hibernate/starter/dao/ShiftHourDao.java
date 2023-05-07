package by.kozlov.hibernate.starter.dao;

import by.kozlov.hibernate.starter.entity.ShiftHour;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class ShiftHourDao implements DaoHibernate<Integer,ShiftHour>{

    private static final ShiftHourDao INSTANCE = new ShiftHourDao();

    private ShiftHourDao() {
    }

    public static ShiftHourDao getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean update(Session session, ShiftHour shiftHour) {
        return false;
    }

    @Override
    public Optional<ShiftHour> findById(Session session, Integer id) {
        return Optional.empty();
    }

    @Override
    public List<ShiftHour> findAll(Session session) {
        return null;
    }

    @Override
    public boolean delete(Session session, Integer id) {
        return false;
    }

    @Override
    public ShiftHour save(Session session, ShiftHour shiftHour) {
        return null;
    }
}
