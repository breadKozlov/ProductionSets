package by.kozlov.jdbc.starter.dto;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CreateMaterialsProductionDto {

    private String material;
    private String brigade;
    private String quantity;
    private String dateOfProduction;
}
