package by.kozlov.jdbc.starter.dto;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class ProductionDto {
        private Integer id;
        private Integer id_worker;
        private String nameOfSets;
        private Integer madeSets;
        private LocalDateTime dateOfProduction;
}
