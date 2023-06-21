package by.kozlov.spring.mapper;

import by.kozlov.spring.database.entity.Material;
import by.kozlov.spring.database.entity.MaterialsProduction;
import by.kozlov.spring.database.entity.Requirement;
import by.kozlov.spring.database.entity.Set;
import by.kozlov.spring.database.repository.MaterialRepository;
import by.kozlov.spring.database.repository.SetRepository;
import by.kozlov.spring.dto.RequirementCreateEditDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RequirementCreateEditMapper implements Mapper<RequirementCreateEditDto, Requirement> {

    private final SetRepository setRepository;
    private final MaterialRepository materialRepository;

    @Override
    public Requirement map(RequirementCreateEditDto object) {
        Requirement requirement = new Requirement();
        copy(object,requirement);
        return requirement;
    }

    @Override
    public Requirement map(RequirementCreateEditDto fromObject, Requirement toObject) {
        copy(fromObject,toObject);
        return toObject;
    }

    @Override
    public void copy(RequirementCreateEditDto object, Requirement requirement) {
        requirement.setSet(getSet(object.getSetId()));
        requirement.setMaterial(getMaterial(object.getMaterialId()));
        requirement.setUnitCost(object.getUnitCost());
        requirement.setTotalSets(object.getTotalSets());
    }

    private Material getMaterial(Integer materialId) {
        return  Optional.ofNullable(materialId)
                .flatMap(materialRepository::findById)
                .orElse(null);
    }

    private Set getSet(Integer setId) {
        return Optional.ofNullable(setId)
                .flatMap(setRepository::findById)
                .orElse(null);
    }
}
