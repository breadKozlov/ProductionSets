package by.kozlov.jdbc.starter.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SetDto {

    private Integer id;
    private String nameOfSet;
    private Integer numberOfPartsIncluded;
    private Double rateOfSet;
}
