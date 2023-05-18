package by.kozlov.hibernate.starter.dto;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotEmpty;

@Value
@Builder
public class UpdateMaterialsProductionDto {

    private String id;
    @NotEmpty
    private String material;
    @NotEmpty
    private String brigade;
    @NotEmpty
    private String quantity;
    @NotEmpty
    private String dateOfProduction;
}
