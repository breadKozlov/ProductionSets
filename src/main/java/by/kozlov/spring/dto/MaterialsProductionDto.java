package by.kozlov.spring.dto;

import lombok.*;

import java.time.LocalDate;

@Builder
@Value
public class MaterialsProductionDto {

    Integer id;
    MaterialDto material;
    BrigadeDto brigade;
    Double quantity;
    LocalDate dateOfProduction;
}