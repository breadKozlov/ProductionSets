package by.kozlov.jdbc.starter.mapper;

import by.kozlov.jdbc.starter.dto.MaterialsProductionDto;
import by.kozlov.jdbc.starter.entity.MaterialsProduction;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class MaterialsProductionMapper implements Mapper<MaterialsProduction, MaterialsProductionDto> {

    private static final MaterialsProductionMapper INSTANCE = new MaterialsProductionMapper();

    @Override
    public MaterialsProductionDto mapFrom(MaterialsProduction object) {
        return MaterialsProductionDto.builder()
                .id(object.getId())
                .material(object.getMaterial())
                .brigade(object.getBrigade())
                .quantity(object.getQuantity())
                .dateOfProduction(object.getDateOfProduction())
                .build();
    }

    public static MaterialsProductionMapper getInstance() {
        return INSTANCE;
    }
}
