package by.kozlov.spring.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class ProductionDto {

    Integer id;
    WorkerDto worker;
    SetDto set;
    Integer madeSets;
    LocalDate dateOfProduction;
}
