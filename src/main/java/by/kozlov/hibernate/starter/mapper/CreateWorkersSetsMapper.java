package by.kozlov.hibernate.starter.mapper;

import by.kozlov.hibernate.starter.dao.SetDao;
import by.kozlov.hibernate.starter.dao.WorkerDao;
import by.kozlov.hibernate.starter.dto.CreateWorkersSetsDto;
import by.kozlov.hibernate.starter.entity.WorkersSets;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateWorkersSetsMapper implements Mapper<CreateWorkersSetsDto, WorkersSets> {

    private static final CreateWorkersSetsMapper INSTANCE = new CreateWorkersSetsMapper();
    private final SetDao setDao = SetDao.getInstance();
    private final WorkerDao workerDao = WorkerDao.getInstance();

    @Override
    public WorkersSets mapFrom(CreateWorkersSetsDto object) {
        return WorkersSets.builder()
                .set(setDao.findById(Integer.parseInt(object.getSet())).orElseThrow())
                .worker(workerDao.findById(Integer.parseInt(object.getWorker())).orElseThrow())
                .requirement(Integer.parseInt(object.getRequirement()))
                .build();
    }

    public static CreateWorkersSetsMapper getInstance() {
        return INSTANCE;
    }
}
