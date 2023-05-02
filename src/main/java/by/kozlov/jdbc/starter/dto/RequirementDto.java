package by.kozlov.jdbc.starter.dto;

import by.kozlov.jdbc.starter.entity.Material;
import by.kozlov.jdbc.starter.entity.Set;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RequirementDto {
    private Integer id;
    private Set set;
    private Material material;
    private Double unitCost;
    private Integer totalSets;
}
