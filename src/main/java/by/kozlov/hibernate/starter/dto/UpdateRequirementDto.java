package by.kozlov.hibernate.starter.dto;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotEmpty;

@Value
@Builder
public class UpdateRequirementDto {

    @NotEmpty
    String id;
    @NotEmpty
    String set;
    @NotEmpty
    String material;
    @NotEmpty
    String unitCost;
    @NotEmpty
    String totalSets;
}
