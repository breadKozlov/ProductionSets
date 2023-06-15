package by.kozlov.spring.mapper;

import by.kozlov.spring.dto.UpdateRequirementDto;
import by.kozlov.spring.database.entity.Requirement;
import by.kozlov.spring.database.repository.MaterialRepository;
import by.kozlov.spring.database.repository.SetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateRequirementMapper implements Mapper<UpdateRequirementDto, Requirement> {
    private final SetRepository setRepository;
    private final MaterialRepository materialRepository;

    @Autowired
    public UpdateRequirementMapper(SetRepository setRepository, MaterialRepository materialRepository) {
        this.setRepository = setRepository;
        this.materialRepository = materialRepository;
    }

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
