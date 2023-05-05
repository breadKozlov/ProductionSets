package by.kozlov.hibernate.starter.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Value
@Builder
public class MaterialDto {

    private Integer id;
    private String nameOfMaterial;
    private String description;
}
