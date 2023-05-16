package by.kozlov.hibernate.starter.mapper;

import by.kozlov.hibernate.starter.dto.ProductionDto;
import by.kozlov.hibernate.starter.entity.Production;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductionMapper implements Mapper<Production, ProductionDto> {

    private final WorkerMapper workerMapper;
    private final SetMapper setMapper;
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
