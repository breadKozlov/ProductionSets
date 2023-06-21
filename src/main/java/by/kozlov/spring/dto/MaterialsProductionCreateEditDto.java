package by.kozlov.spring.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Builder
@Value
public class MaterialsProductionCreateEditDto {

    @NotEmpty
    Integer materialId;
    @NotEmpty
    Integer brigadeId;
    @NotEmpty
    Double quantity;
    @NotEmpty
    LocalDate dateOfProduction;
}
