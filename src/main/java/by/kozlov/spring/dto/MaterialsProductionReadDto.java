package by.kozlov.spring.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Builder
@Value
public class MaterialsProductionReadDto {
    Integer id;
    MaterialReadDto material;
    BrigadeReadDto brigade;
    Double quantity;
    LocalDate dateOfProduction;
}
