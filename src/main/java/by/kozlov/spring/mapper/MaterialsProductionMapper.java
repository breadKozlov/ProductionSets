package by.kozlov.spring.mapper;

import by.kozlov.spring.dto.MaterialsProductionDto;
import by.kozlov.spring.database.entity.MaterialsProduction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MaterialsProductionMapper implements Mapper<MaterialsProduction, MaterialsProductionDto> {

    private final MaterialMapper materialMapper;
    private final BrigadeMapper brigadeMapper;

    @Autowired
    public MaterialsProductionMapper(MaterialMapper materialMapper, BrigadeMapper brigadeMapper) {
        this.materialMapper = materialMapper;
        this.brigadeMapper = brigadeMapper;
    }

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
