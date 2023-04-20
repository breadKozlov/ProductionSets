package by.kozlov.jdbc.starter.mapper;

import by.kozlov.jdbc.starter.dto.WorkersSetsDto;
import by.kozlov.jdbc.starter.entity.WorkersSets;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class WorkersSetsMapper implements Mapper<WorkersSets, WorkersSetsDto>{

    private static final WorkersSetsMapper INSTANCE = new WorkersSetsMapper();

    @Override
    public WorkersSetsDto mapFrom(WorkersSets object) {
        return WorkersSetsDto.builder()
                .id(object.getId())
                .set(object.getSet())
                .worker(object.getWorker())
                .requirement(object.getRequirement())
                .build();
    }

    public static WorkersSetsMapper getInstance() {
        return INSTANCE;
    }
}
