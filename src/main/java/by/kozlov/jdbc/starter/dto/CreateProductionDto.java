package by.kozlov.jdbc.starter.dto;

import by.kozlov.jdbc.starter.entity.Set;
import by.kozlov.jdbc.starter.entity.Worker;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class CreateProductionDto {
    private String worker;
    private String set;
    private String madeSets;
    private String dateOfProduction;
}
