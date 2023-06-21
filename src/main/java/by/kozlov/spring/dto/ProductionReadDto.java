package by.kozlov.spring.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class ProductionReadDto {
    Integer id;
    WorkerReadDto worker;
    SetReadDto set;
    Integer madeSets;
    LocalDate dateOfProduction;
}
