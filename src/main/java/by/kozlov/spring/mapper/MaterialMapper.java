package by.kozlov.spring.mapper;

import by.kozlov.spring.dto.MaterialDto;
import by.kozlov.spring.entity.Material;
import org.springframework.stereotype.Component;

@Component
public class MaterialMapper implements Mapper<Material, MaterialDto> {

    @Override
    public MaterialDto mapFrom(Material object) {
        return MaterialDto.builder()
                .id(object.getId())
                .nameOfMaterial(object.getNameOfMaterial())
                .description(object.getDescription())
                .build();
    }
}
