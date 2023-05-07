package by.kozlov.hibernate.starter.mapper;

import by.kozlov.hibernate.starter.dao.SetDao;
import by.kozlov.hibernate.starter.dao.WorkerDao;
import by.kozlov.hibernate.starter.dto.CreateWorkersSetsDto;
import by.kozlov.hibernate.starter.entity.Requirement;
import by.kozlov.hibernate.starter.entity.WorkersSets;
import by.kozlov.hibernate.starter.utils.HibernateUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.SessionFactory;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateWorkersSetsMapper implements Mapper<CreateWorkersSetsDto, WorkersSets> {

    private static final CreateWorkersSetsMapper INSTANCE = new CreateWorkersSetsMapper();
    private final SetDao setDao = SetDao.getInstance();
    private final WorkerDao workerDao = WorkerDao.getInstance();
    private final SessionFactory sessionFactory = HibernateUtil.getConfig().buildSessionFactory();

    @Override
    public WorkersSets mapFrom(CreateWorkersSetsDto object) {

        try (var session = sessionFactory.openSession()) {

            WorkersSets workersSets;
            session.beginTransaction();
            workersSets = WorkersSets.builder()
                    .set(setDao.findById(session, Integer.parseInt(object.getSet())).orElseThrow())
                    .worker(workerDao.findById(session, Integer.parseInt(object.getWorker())).orElseThrow())
                    .requirement(Integer.parseInt(object.getRequirement()))
                    .build();
            session.getTransaction().commit();
            return workersSets;
        }
    }


    public static CreateWorkersSetsMapper getInstance() {
        return INSTANCE;
    }
}
