package by.kozlov.jdbc.starter.mapper;

import by.kozlov.jdbc.starter.dto.MaterialDto;
import by.kozlov.jdbc.starter.entity.Material;

public class MaterialMapper implements Mapper<Material, MaterialDto> {

    private static final MaterialMapper INSTANCE = new MaterialMapper();

    @Override
    public MaterialDto mapFrom(Material object) {
        return MaterialDto.builder()
                .id(object.getId())
                .nameOfMaterial(object.getNameOfMaterial())
                .description(object.getDescription())
                .build();
    }

    public static MaterialMapper getInstance() {
        return INSTANCE;
    }


}
