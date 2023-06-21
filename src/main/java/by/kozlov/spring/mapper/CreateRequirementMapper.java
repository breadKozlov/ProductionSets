package by.kozlov.spring.mapper;

import by.kozlov.spring.dto.CreateRequirementDto;
import by.kozlov.spring.database.entity.Requirement;
import by.kozlov.spring.database.repository.MaterialRepository;
import by.kozlov.spring.database.repository.SetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateRequirementMapper implements OlderMapper<CreateRequirementDto, Requirement> {

    private final SetRepository setRepository;
    private final MaterialRepository materialRepository;

    @Autowired
    public CreateRequirementMapper(SetRepository setRepository, MaterialRepository materialRepository) {
        this.setRepository = setRepository;
        this.materialRepository = materialRepository;
    }

    @Override
    public Requirement mapFrom(CreateRequirementDto object) {
        return Requirement.builder()
                .set(setRepository.findById(Integer.parseInt(object.getSet())).orElseThrow())
                .material(materialRepository.findById(Integer.parseInt(object.getMaterial())).orElseThrow())
                .unitCost(Double.parseDouble(object.getUnitCost()))
                .totalSets(Integer.parseInt(object.getTotalSets()))
                .build();

    }
}
