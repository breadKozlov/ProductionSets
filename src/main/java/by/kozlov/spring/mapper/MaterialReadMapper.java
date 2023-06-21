package by.kozlov.spring.mapper;

import by.kozlov.spring.database.entity.Material;
import by.kozlov.spring.dto.MaterialReadDto;

public class MaterialReadMapper implements Mapper<Material, MaterialReadDto> {

    @Override
    public MaterialReadDto map(Material object) {
        return MaterialReadDto.builder()
                .id(object.getId())
                .nameOfMaterial(object.getNameOfMaterial())
                .description(object.getDescription())
                .build();
    }
}
