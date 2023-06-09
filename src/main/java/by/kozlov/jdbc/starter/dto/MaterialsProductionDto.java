package by.kozlov.jdbc.starter.dto;

import by.kozlov.jdbc.starter.entity.Brigade;
import by.kozlov.jdbc.starter.entity.Material;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Builder
@Value
public class MaterialsProductionDto {

    private Integer id;
    private Material material;
    private Brigade brigade;
    private Double quantity;
    private LocalDate dateOfProduction;
}
