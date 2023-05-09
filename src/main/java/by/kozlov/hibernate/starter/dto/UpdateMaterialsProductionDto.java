package by.kozlov.hibernate.starter.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UpdateMaterialsProductionDto {

    private String id;
    private String material;
    private String brigade;
    private String quantity;
    private String dateOfProduction;
}
