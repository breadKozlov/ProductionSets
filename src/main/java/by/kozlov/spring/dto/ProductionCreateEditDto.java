package by.kozlov.spring.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class ProductionCreateEditDto {

    @NotNull
    Integer workerId;
    @NotNull
    Integer setId;
    @NotNull
    Integer madeSets;
    @NotNull(message = "Date of production is not be empty")
    //@DateTimeFormat(pattern = "dd-MM-yyyy")
    LocalDate dateOfProduction;
}
