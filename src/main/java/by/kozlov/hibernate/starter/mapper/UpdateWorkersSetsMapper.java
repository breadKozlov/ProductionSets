package by.kozlov.hibernate.starter.mapper;

import by.kozlov.hibernate.starter.dao.SetDao;
import by.kozlov.hibernate.starter.dao.SetRepository;
import by.kozlov.hibernate.starter.dao.WorkerDao;
import by.kozlov.hibernate.starter.dao.WorkerRepository;
import by.kozlov.hibernate.starter.dto.UpdateWorkersSetsDto;
import by.kozlov.hibernate.starter.entity.WorkersSets;
import by.kozlov.hibernate.starter.utils.HibernateUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;

@RequiredArgsConstructor
public class UpdateWorkersSetsMapper implements Mapper<UpdateWorkersSetsDto, WorkersSets> {

    private final SetRepository setRepository;
    private final WorkerRepository workerRepository;

    @Override
    public WorkersSets mapFrom(UpdateWorkersSetsDto object) {

            return WorkersSets.builder()
                    .id(Integer.parseInt(object.getId()))
                    .set(setRepository.findById(Integer.parseInt(object.getSet())).orElseThrow())
                    .worker(workerRepository.findById(Integer.parseInt(object.getWorker())).orElseThrow())
                    .requirement(Integer.parseInt(object.getRequirement()))
                    .build();
    }
}
