package by.kozlov.hibernate.starter.dao;

import by.kozlov.hibernate.starter.entity.Schedule;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class ScheduleDao implements DaoHibernate<Integer,Schedule> {

    private static final ScheduleDao INSTANCE = new ScheduleDao();

    private static final String FIND_ALL_HQL = """
            FROM Production P JOIN FETCH P.set JOIN FETCH P.worker JOIN FETCH P.worker.brigade
            """;

    private static final String FIND_BY_ID_HQL = FIND_ALL_HQL + """
             WHERE P.id = :id
            """;

    private static final String DELETE_HQL = """
            DELETE Production P WHERE P.id = :id
            """;

    private static final String FIND_BY_ID_WORKER_HQL = FIND_ALL_HQL + """
             WHERE P.worker.id = :id
            """;


    private ScheduleDao() {}

    public static ScheduleDao getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean update(Session session, Schedule schedule) {
        return false;
    }

    @Override
    public Optional<Schedule> findById(Session session, Integer id) {
        return Optional.empty();
    }

    @Override
    public List<Schedule> findAll(Session session) {
        return null;
    }

    @Override
    public boolean delete(Session session, Integer id) {
        return false;
    }

    @Override
    public Schedule save(Session session, Schedule schedule) {
        return null;
    }
}
