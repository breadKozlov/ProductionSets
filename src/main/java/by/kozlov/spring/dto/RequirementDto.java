package by.kozlov.spring.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RequirementDto {
    Integer id;
    SetDto set;
    MaterialDto material;
    Double unitCost;
    Integer totalSets;
}
