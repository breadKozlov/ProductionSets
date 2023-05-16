package by.kozlov.hibernate.starter.dto;

import by.kozlov.hibernate.starter.entity.Material;
import by.kozlov.hibernate.starter.entity.Set;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RequirementDto {
    private Integer id;
    private SetDto set;
    private MaterialDto material;
    private Double unitCost;
    private Integer totalSets;
}
