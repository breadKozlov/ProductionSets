package by.kozlov.spring.mapper;

import by.kozlov.spring.dto.CreateWorkersSetsDto;
import by.kozlov.spring.database.entity.WorkersSets;
import by.kozlov.spring.database.repository.SetRepository;
import by.kozlov.spring.database.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateWorkersSetsMapper implements OlderMapper<CreateWorkersSetsDto, WorkersSets> {
    private final SetRepository setRepository;
    private final WorkerRepository workerRepository;

    @Autowired
    public CreateWorkersSetsMapper(SetRepository setRepository, WorkerRepository workerRepository) {
        this.setRepository = setRepository;
        this.workerRepository = workerRepository;
    }

    @Override
    public WorkersSets mapFrom(CreateWorkersSetsDto object) {

        return WorkersSets.builder()
                .set(setRepository.findById(Integer.parseInt(object.getSet())).orElseThrow())
                .worker(workerRepository.findById(Integer.parseInt(object.getWorker())).orElseThrow())
                .requirement(Integer.parseInt(object.getRequirement()))
                .build();
    }
}
