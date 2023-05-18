package by.kozlov.hibernate.starter.dto;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotEmpty;

@Value
@Builder
public class UpdateRequirementDto {

    @NotEmpty
    private String id;
    @NotEmpty
    private String set;
    @NotEmpty
    private String material;
    @NotEmpty
    private String unitCost;
    @NotEmpty
    private String totalSets;
}
