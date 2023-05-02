package by.kozlov.jdbc.starter.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Requirement {

    private Integer id;
    private Set set;
    private Material material;
    private Double unitCost;
    private Integer totalSets;
}
