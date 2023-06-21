package by.kozlov.spring.mapper;

import by.kozlov.spring.database.entity.Production;
import by.kozlov.spring.dto.ProductionReadDto;
import by.kozlov.spring.dto.SetReadDto;
import by.kozlov.spring.dto.WorkerReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductionReadMapper implements Mapper<Production,ProductionReadDto>{

    private final WorkerReadMapper workerReadMapper;
    private final SetReadMapper setReadMapper;
    @Override
    public ProductionReadDto map(Production object) {

        WorkerReadDto workerReadDto = Optional.ofNullable(object.getWorker())
                .map(workerReadMapper::map)
                .orElse(null);

        SetReadDto setReadDto = Optional.ofNullable(object.getSet())
                .map(setReadMapper::map)
                .orElse(null);

        return ProductionReadDto.builder()
                .id(object.getId())
                .worker(workerReadDto)
                .set(setReadDto)
                .madeSets(object.getMadeSets())
                .dateOfProduction(object.getDateOfProduction())
                .build();
    }
}
