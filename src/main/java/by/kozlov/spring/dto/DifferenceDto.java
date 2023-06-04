package by.kozlov.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DifferenceDto {

    private String nameOfProduct;
    private Double requiredQuantity;
    private Double releasedQuantity;

    public Double difference() {
        return requiredQuantity - releasedQuantity;
    }
}
