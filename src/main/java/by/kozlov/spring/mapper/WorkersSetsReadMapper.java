package by.kozlov.spring.mapper;

import by.kozlov.spring.database.entity.WorkersSets;
import by.kozlov.spring.dto.SetReadDto;
import by.kozlov.spring.dto.WorkerReadDto;
import by.kozlov.spring.dto.WorkersSetsReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class WorkersSetsReadMapper implements Mapper<WorkersSets, WorkersSetsReadDto> {

    private final WorkerReadMapper workerReadMapper;
    private final SetReadMapper setReadMapper;

    @Override
    public WorkersSetsReadDto map(WorkersSets object) {

        WorkerReadDto workerReadDto = Optional.ofNullable(object.getWorker())
                .map(workerReadMapper::map)
                .orElse(null);

        SetReadDto setReadDto = Optional.ofNullable(object.getSet())
                .map(setReadMapper::map)
                .orElse(null);

        return WorkersSetsReadDto.builder()
                .id(object.getId())
                .set(setReadDto)
                .worker(workerReadDto)
                .requirement(object.getRequirement())
                .build();
    }
}
