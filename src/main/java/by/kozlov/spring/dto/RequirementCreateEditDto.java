package by.kozlov.spring.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RequirementCreateEditDto {
    Integer id;
    Integer setId;
    Integer materialId;
    Double unitCost;
    Integer totalSets;
}
