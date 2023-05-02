package by.kozlov.jdbc.starter.dto;

import by.kozlov.jdbc.starter.entity.Set;
import by.kozlov.jdbc.starter.entity.Worker;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class ProductionDto {

        private Integer id;
        private Worker worker;
        private Set set;
        private Integer madeSets;
        private LocalDate dateOfProduction;
}
