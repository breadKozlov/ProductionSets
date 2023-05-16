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
        private WorkerDto worker;
        private SetDto set;
        private Integer madeSets;
        private LocalDate dateOfProduction;
}
