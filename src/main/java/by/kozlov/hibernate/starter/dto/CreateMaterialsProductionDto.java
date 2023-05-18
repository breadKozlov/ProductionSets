package by.kozlov.hibernate.starter.dto;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotEmpty;

@Builder
@Value
public class CreateMaterialsProductionDto {

    @NotEmpty
    private String material;
    @NotEmpty
    private String brigade;
    @NotEmpty
    private String quantity;
    @NotEmpty
    private String dateOfProduction;
}
