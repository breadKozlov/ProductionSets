package by.kozlov.hibernate.starter.dto;

import by.kozlov.hibernate.starter.entity.Set;
import by.kozlov.hibernate.starter.entity.Worker;
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
