package by.kozlov.hibernate.starter.mapper;

import by.kozlov.hibernate.starter.dto.RequirementDto;
import by.kozlov.hibernate.starter.entity.Requirement;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RequirementMapper implements Mapper<Requirement, RequirementDto>{


    private final SetMapper setMapper;
    private final MaterialMapper materialMapper;

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
