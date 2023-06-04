package by.kozlov.spring.dto;

import lombok.Builder;
import lombok.Value;
import jakarta.validation.constraints.NotEmpty;
@Builder
@Value
public class CreateMaterialsProductionDto {

    @NotEmpty
    String material;
    @NotEmpty
    String brigade;
    @NotEmpty
    String quantity;
    @NotEmpty
    String dateOfProduction;
}
