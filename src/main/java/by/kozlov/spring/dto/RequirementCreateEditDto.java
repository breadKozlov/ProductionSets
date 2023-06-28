package by.kozlov.spring.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RequirementCreateEditDto {

    @NotNull
    Integer setId;
    @NotNull
    Integer materialId;
    @NotNull
    Double unitCost;
    @NotNull
    Integer totalSets;
}
