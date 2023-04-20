package by.kozlov.jdbc.starter.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Set {

    private Integer id;
    private String nameOfSet;
    private Integer numberOfPartsIncluded;
    private Double rateOfSet;

}
