package by.kozlov.hibernate.starter.mapper;

import by.kozlov.hibernate.starter.dao.SetDao;
import by.kozlov.hibernate.starter.dao.SetRepository;
import by.kozlov.hibernate.starter.dao.WorkerDao;
import by.kozlov.hibernate.starter.dao.WorkerRepository;
import by.kozlov.hibernate.starter.dto.CreateWorkersSetsDto;
import by.kozlov.hibernate.starter.entity.Requirement;
import by.kozlov.hibernate.starter.entity.WorkersSets;
import by.kozlov.hibernate.starter.utils.HibernateUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;

@RequiredArgsConstructor
public class CreateWorkersSetsMapper implements Mapper<CreateWorkersSetsDto, WorkersSets> {
    private final SetRepository setRepository;
    private final WorkerRepository workerRepository;

    @Override
    public WorkersSets mapFrom(CreateWorkersSetsDto object) {

            return WorkersSets.builder()
                    .set(setRepository.findById(Integer.parseInt(object.getSet())).orElseThrow())
                    .worker(workerRepository.findById(Integer.parseInt(object.getWorker())).orElseThrow())
                    .requirement(Integer.parseInt(object.getRequirement()))
                    .build();
    }
}
