package by.kozlov.spring.mapper;

import by.kozlov.spring.dto.RequirementDto;
import by.kozlov.spring.database.entity.Requirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RequirementMapper implements OlderMapper<Requirement, RequirementDto> {
    private final SetMapper setMapper;
    private final MaterialMapper materialMapper;

    @Autowired
    public RequirementMapper(SetMapper setMapper, MaterialMapper materialMapper) {
        this.setMapper = setMapper;
        this.materialMapper = materialMapper;
    }

    @Override
    public RequirementDto mapFrom(Requirement object) {
        return RequirementDto.builder()
                .id(object.getId())
                .set(setMapper.mapFrom(object.getSet()))
                .material(materialMapper.mapFrom(object.getMaterial()))
                .unitCost(object.getUnitCost())
                .totalSets(object.getTotalSets())
                .build();
    }
}
