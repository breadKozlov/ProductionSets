package by.kozlov.spring.dto;

import lombok.*;
@Value
@Builder
public class SetDto {

    Integer id;
    String nameOfSet;
    Integer numberOfPartsIncluded;
    Double rateOfSet;
}
