package by.kozlov.spring.mapper;

import by.kozlov.spring.database.entity.Set;
import by.kozlov.spring.database.entity.Worker;
import by.kozlov.spring.database.entity.WorkersSets;
import by.kozlov.spring.database.repository.SetRepository;
import by.kozlov.spring.database.repository.WorkerRepository;
import by.kozlov.spring.dto.WorkersSetsCreateEditDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class WorkersSetsCreateEditMapper implements Mapper<WorkersSetsCreateEditDto, WorkersSets> {

    private final SetRepository setRepository;
    private final WorkerRepository workerRepository;


    private Worker getWorker(Integer workerId) {
        return  Optional.ofNullable(workerId)
                .flatMap(workerRepository::findById)
                .orElse(null);
    }

    private Set getSet(Integer setId) {
        return Optional.ofNullable(setId)
                .flatMap(setRepository::findById)
                .orElse(null);
    }

    @Override
    public WorkersSets map(WorkersSetsCreateEditDto object) {
        WorkersSets workersSets = new WorkersSets();
        copy(object,workersSets);
        return workersSets;
    }

    @Override
    public WorkersSets map(WorkersSetsCreateEditDto fromObject, WorkersSets toObject) {
        copy(fromObject,toObject);
        return toObject;
    }

    @Override
    public void copy(WorkersSetsCreateEditDto fromObject, WorkersSets toObject) {
        toObject.setWorker(getWorker(fromObject.getWorkerId()));
        toObject.setSet(getSet(fromObject.getSetId()));
        toObject.setRequirement(fromObject.getRequirement());
    }
}
