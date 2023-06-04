package by.kozlov.spring.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UpdateProductionDto {

    @NotEmpty
    String id;
    @NotEmpty
    String worker;
    @NotEmpty
    String set;
    @NotEmpty
    String madeSets;
    @NotEmpty
    String dateOfProduction;
}
