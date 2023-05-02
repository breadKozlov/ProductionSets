package by.kozlov.jdbc.starter.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UpdateRequirementDto {

    private String set;
    private String material;
    private String unitCost;
    private String totalSets;
}
