package by.kozlov.spring.mapper;

import by.kozlov.spring.dto.UpdateWorkersSetsDto;
import by.kozlov.spring.entity.WorkersSets;
import by.kozlov.spring.repository.SetRepository;
import by.kozlov.spring.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateWorkersSetsMapper implements Mapper<UpdateWorkersSetsDto, WorkersSets> {

    private final SetRepository setRepository;
    private final WorkerRepository workerRepository;

    @Autowired
    public UpdateWorkersSetsMapper(SetRepository setRepository, WorkerRepository workerRepository) {
        this.setRepository = setRepository;
        this.workerRepository = workerRepository;
    }

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
