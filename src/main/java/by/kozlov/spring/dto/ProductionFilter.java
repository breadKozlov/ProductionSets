package by.kozlov.spring.dto;

import by.kozlov.spring.database.entity.Set;
import by.kozlov.spring.database.entity.Worker;

import java.time.LocalDate;

public record ProductionFilter(Integer workerId,
                               Integer setId,
                               LocalDate dateOfProduction) {
}
