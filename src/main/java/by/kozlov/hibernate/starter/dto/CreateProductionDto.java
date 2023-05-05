package by.kozlov.hibernate.starter.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateProductionDto {

    private String worker;
    private String set;
    private String madeSets;
    private String dateOfProduction;
}
