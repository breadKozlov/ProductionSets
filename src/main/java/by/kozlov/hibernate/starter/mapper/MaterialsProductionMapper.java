package by.kozlov.hibernate.starter.mapper;

import by.kozlov.hibernate.starter.entity.MaterialsProduction;
import by.kozlov.hibernate.starter.dto.MaterialsProductionDto;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor
public class MaterialsProductionMapper implements Mapper<MaterialsProduction, MaterialsProductionDto> {

    private final MaterialMapper materialMapper;
    private final BrigadeMapper brigadeMapper;
    @Override
    public MaterialsProductionDto mapFrom(MaterialsProduction object) {
        return MaterialsProductionDto.builder()
                .id(object.getId())
                .material(materialMapper.mapFrom(object.getMaterial()))
                .brigade(brigadeMapper.mapFrom(object.getBrigade()))
                .quantity(object.getQuantity())
                .dateOfProduction(object.getDateOfProduction())
                .build();
    }
}
