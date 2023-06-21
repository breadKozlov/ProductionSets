package by.kozlov.spring.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RequirementCreateEditDto {

    @NotEmpty
    Integer setId;
    @NotEmpty
    Integer materialId;
    @NotEmpty
    Double unitCost;
    @NotEmpty
    Integer totalSets;
}
