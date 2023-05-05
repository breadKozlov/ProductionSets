package by.kozlov.hibernate.starter.mapper;

import by.kozlov.hibernate.starter.dto.MaterialDto;
import by.kozlov.hibernate.starter.entity.Material;

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
