package by.kozlov.hibernate.starter.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MaterialsProduction {

    private Integer id;
    private Material material;
    private Brigade brigade;
    private Double quantity;
    private LocalDate dateOfProduction;
}
