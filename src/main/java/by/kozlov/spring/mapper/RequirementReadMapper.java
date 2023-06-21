package by.kozlov.spring.mapper;

import by.kozlov.spring.database.entity.Requirement;
import by.kozlov.spring.dto.MaterialReadDto;
import by.kozlov.spring.dto.RequirementReadDto;
import by.kozlov.spring.dto.SetReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RequirementReadMapper implements Mapper<Requirement, RequirementReadDto> {

    private final SetReadMapper setReadMapper;
    private final MaterialReadMapper materialsReadMapper;


    @Override
    public RequirementReadDto map(Requirement object) {

        SetReadDto setReadDto = Optional.ofNullable(object.getSet())
                .map(setReadMapper::map)
                .orElse(null);

        MaterialReadDto materialReadDto = Optional.ofNullable(object.getMaterial())
                .map(materialsReadMapper::map)
                .orElse(null);

        return RequirementReadDto.builder()
                .id(object.getId())
                .set(setReadDto)
                .material(materialReadDto)
                .unitCost(object.getUnitCost())
                .totalSets(object.getTotalSets())
                .build();
    }
}
