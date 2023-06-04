package by.kozlov.spring.mapper;

import by.kozlov.spring.dto.WorkersSetsDto;
import by.kozlov.spring.entity.WorkersSets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WorkersSetsMapper implements Mapper<WorkersSets, WorkersSetsDto>{

    private final SetMapper setMapper;
    private final WorkerMapper workerMapper;

    @Autowired
    public WorkersSetsMapper(SetMapper setMapper, WorkerMapper workerMapper) {
        this.setMapper = setMapper;
        this.workerMapper = workerMapper;
    }

    @Override
    public WorkersSetsDto mapFrom(WorkersSets object) {
        return WorkersSetsDto.builder()
                .id(object.getId())
                .set(setMapper.mapFrom(object.getSet()))
                .worker(workerMapper.mapFrom(object.getWorker()))
                .requirement(object.getRequirement())
                .build();
    }

}
