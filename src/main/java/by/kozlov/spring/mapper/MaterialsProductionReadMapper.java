package by.kozlov.spring.mapper;

import by.kozlov.spring.database.entity.MaterialsProduction;
import by.kozlov.spring.dto.BrigadeReadDto;
import by.kozlov.spring.dto.MaterialReadDto;
import by.kozlov.spring.dto.MaterialsProductionReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MaterialsProductionReadMapper implements Mapper<MaterialsProduction, MaterialsProductionReadDto> {

    private final MaterialsReadMapper materialReadMapper;
    private final BrigadeReadMapper brigadeReadMapper;

    @Override
    public MaterialsProductionReadDto map(MaterialsProduction object) {

        BrigadeReadDto brigadeReadDto = Optional.ofNullable(object.getBrigade())
                .map(brigadeReadMapper::map)
                .orElse(null);

        MaterialReadDto materialReadDto = Optional.ofNullable(object.getMaterial())
                .map(materialReadMapper::map)
                .orElse(null);

        return MaterialsProductionReadDto.builder()
                .id(object.getId())
                .material(materialReadDto)
                .brigade(brigadeReadDto)
                .quantity(object.getQuantity())
                .dateOfProduction(object.getDateOfProduction())
                .build();
    }
}
