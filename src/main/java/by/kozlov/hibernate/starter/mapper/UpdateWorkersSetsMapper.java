package by.kozlov.hibernate.starter.mapper;

import by.kozlov.hibernate.starter.dao.SetDao;
import by.kozlov.hibernate.starter.dao.WorkerDao;
import by.kozlov.hibernate.starter.dto.UpdateWorkersSetsDto;
import by.kozlov.hibernate.starter.entity.WorkersSets;
import by.kozlov.hibernate.starter.utils.HibernateUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.SessionFactory;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateWorkersSetsMapper implements Mapper<UpdateWorkersSetsDto, WorkersSets> {

    private static final UpdateWorkersSetsMapper INSTANCE = new UpdateWorkersSetsMapper();
    private final SetDao setDao = SetDao.getInstance();
    private final WorkerDao workerDao = WorkerDao.getInstance();
    private final SessionFactory sessionFactory = HibernateUtil.getConfig().buildSessionFactory();

    @Override
    public WorkersSets mapFrom(UpdateWorkersSetsDto object) {


        try (var session = sessionFactory.openSession()) {

            WorkersSets workersSets;
            session.beginTransaction();
            workersSets = WorkersSets.builder()
                    .id(Integer.parseInt(object.getId()))
                    .set(setDao.findById(session, Integer.parseInt(object.getSet())).orElseThrow())
                    .worker(workerDao.findById(session, Integer.parseInt(object.getWorker())).orElseThrow())
                    .requirement(Integer.parseInt(object.getRequirement()))
                    .build();
            session.getTransaction().commit();
            return workersSets;
        }
    }

    public static UpdateWorkersSetsMapper getInstance() {
        return INSTANCE;
    }
}
