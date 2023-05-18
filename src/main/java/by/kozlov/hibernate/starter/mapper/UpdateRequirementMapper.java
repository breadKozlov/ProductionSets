package by.kozlov.hibernate.starter.mapper;

import by.kozlov.hibernate.starter.dao.MaterialRepository;
import by.kozlov.hibernate.starter.dao.SetRepository;
import by.kozlov.hibernate.starter.dto.UpdateRequirementDto;
import by.kozlov.hibernate.starter.entity.Requirement;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateRequirementMapper implements Mapper<UpdateRequirementDto, Requirement> {
    private final SetRepository setRepository;
    private final MaterialRepository materialRepository;

    @Override
    public Requirement mapFrom(UpdateRequirementDto object) {

            return Requirement.builder()
                    .id(Integer.parseInt(object.getId()))
                    .set(setRepository.findById(Integer.parseInt(object.getSet())).orElseThrow())
                    .material(materialRepository.findById(Integer.parseInt(object.getMaterial())).orElseThrow())
                    .unitCost(Double.parseDouble(object.getUnitCost()))
                    .totalSets(Integer.parseInt(object.getTotalSets()))
                    .build();
    }
}
