package by.kozlov.hibernate.starter.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DifferenceMaterialsDto {

    private String nameOfMaterial;
    private Double requiredMaterial = 0.0;
    private Double releasedMaterial = 0.0;

    public Double difference() {
        return requiredMaterial - releasedMaterial;
    }
}
