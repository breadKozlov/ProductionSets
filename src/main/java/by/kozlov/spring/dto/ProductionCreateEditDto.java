package by.kozlov.spring.dto;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class ProductionCreateEditDto {

    @NotEmpty
    Integer workerId;
    @NotEmpty
    Integer setId;
    @NotEmpty
    Integer madeSets;
    @NotEmpty
    LocalDate dateOfProduction;
}
