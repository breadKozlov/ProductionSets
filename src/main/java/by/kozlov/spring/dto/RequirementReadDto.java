package by.kozlov.spring.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RequirementReadDto {
    Integer id;
    SetReadDto set;
    MaterialReadDto material;
    Double unitCost;
    Integer totalSets;
}
