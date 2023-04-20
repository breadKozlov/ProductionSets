package by.kozlov.jdbc.starter.mapper;

import by.kozlov.jdbc.starter.dto.ProductionDto;
import by.kozlov.jdbc.starter.entity.Production;
import lombok.NoArgsConstructor;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ProductionMapper implements Mapper<Production, ProductionDto> {

    private static final ProductionMapper INSTANCE = new ProductionMapper();

    @Override
    public ProductionDto mapFrom(Production object) {
        return ProductionDto.builder()
                .id(object.getId())
                .worker(object.getWorker())
                .set(object.getSet())
                .madeSets(object.getMadeSets())
                .dateOfProduction(object.getDateOfProduction())
                .build();
    }

    public static ProductionMapper getInstance() {
        return INSTANCE;
    }
}
