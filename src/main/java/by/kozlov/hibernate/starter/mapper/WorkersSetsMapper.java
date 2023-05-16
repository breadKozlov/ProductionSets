package by.kozlov.hibernate.starter.mapper;

import by.kozlov.hibernate.starter.dto.WorkersSetsDto;
import by.kozlov.hibernate.starter.entity.WorkersSets;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor
public class WorkersSetsMapper implements Mapper<WorkersSets, WorkersSetsDto>{

    private final SetMapper setMapper;
    private final WorkerMapper workerMapper;
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
