package by.kozlov.spring.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class MaterialReadDto {
    Integer id;
    String nameOfMaterial;
    String description;
}
