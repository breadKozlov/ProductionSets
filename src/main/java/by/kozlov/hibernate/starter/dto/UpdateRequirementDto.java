package by.kozlov.hibernate.starter.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UpdateRequirementDto {

    private String id;
    private String set;
    private String material;
    private String unitCost;
    private String totalSets;
}
