package by.kozlov.spring.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Builder
@Value
public class MaterialsProductionCreateEditDto {

    @NotNull
    Integer materialId;
    @NotNull
    Integer brigadeId;
    @NotNull
    Double quantity;
    @NotNull
    LocalDate dateOfProduction;
}
