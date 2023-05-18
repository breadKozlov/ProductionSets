package by.kozlov.hibernate.starter.dto;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotEmpty;

@Value
@Builder
public class UpdateProductionDto {

    @NotEmpty
    private String id;
    @NotEmpty
    private String worker;
    @NotEmpty
    private String set;
    @NotEmpty
    private String madeSets;
    @NotEmpty
    private String dateOfProduction;
}
