package by.kozlov.hibernate.starter.dao;

import by.kozlov.hibernate.starter.entity.Schedule;

import javax.persistence.EntityManager;

public class ScheduleRepository extends BaseRepository<Integer, Schedule> {
    public ScheduleRepository(EntityManager entityManager) {
        super(Schedule.class, entityManager);
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
}
