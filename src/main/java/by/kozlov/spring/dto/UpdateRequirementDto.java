package by.kozlov.spring.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Value;

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
