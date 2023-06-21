package by.kozlov.spring.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SetReadDto {
    Integer id;
    String nameOfSet;
    Integer numberOfPartsIncluded;
    Double rateOfSet;
}
