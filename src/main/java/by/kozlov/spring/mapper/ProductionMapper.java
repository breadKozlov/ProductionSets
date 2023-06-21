package by.kozlov.spring.mapper;

import by.kozlov.spring.dto.ProductionDto;
import by.kozlov.spring.database.entity.Production;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductionMapper implements OlderMapper<Production, ProductionDto> {

    private final WorkerMapper workerMapper;
    private final SetMapper setMapper;

    @Autowired
    public ProductionMapper(WorkerMapper workerMapper, SetMapper setMapper) {
        this.workerMapper = workerMapper;
        this.setMapper = setMapper;
    }

    @Override
    public ProductionDto mapFrom(Production object) {
        return ProductionDto.builder()
                .id(object.getId())
                .worker(workerMapper.mapFrom(object.getWorker()))
                .set(setMapper.mapFrom(object.getSet()))
                .madeSets(object.getMadeSets())
                .dateOfProduction(object.getDateOfProduction())
                .build();
    }
}
