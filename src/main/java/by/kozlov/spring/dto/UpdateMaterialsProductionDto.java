package by.kozlov.spring.dto;

import lombok.*;
import jakarta.validation.constraints.NotEmpty;
@Value
@Builder
public class UpdateMaterialsProductionDto {

    String id;
    @NotEmpty
    String material;
    @NotEmpty
    String brigade;
    @NotEmpty
    String quantity;
    @NotEmpty
    String dateOfProduction;
}

