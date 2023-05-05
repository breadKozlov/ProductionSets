package by.kozlov.hibernate.starter.mapper;

import by.kozlov.hibernate.starter.dto.RequirementDto;
import by.kozlov.hibernate.starter.entity.Requirement;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class RequirementMapper implements Mapper<Requirement, RequirementDto>{

    private static final RequirementMapper INSTANCE = new RequirementMapper();

    @Override
    public RequirementDto mapFrom(Requirement object) {
        return RequirementDto.builder()
                .id(object.getId())
                .set(object.getSet())
                .material(object.getMaterial())
                .unitCost(object.getUnitCost())
                .totalSets(object.getTotalSets())
                .build();
    }

    public static RequirementMapper getInstance() {
        return INSTANCE;
    }
}
