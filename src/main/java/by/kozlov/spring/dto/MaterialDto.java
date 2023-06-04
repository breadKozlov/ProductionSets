package by.kozlov.spring.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class MaterialDto {

    Integer id;
    String nameOfMaterial;
    String description;
}
