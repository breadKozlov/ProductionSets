package by.kozlov.spring.database.repository;

import by.kozlov.spring.database.entity.Production;
import by.kozlov.spring.dto.ProductionFilter;

import java.util.List;

public interface FilterProductionRepository {
    List<Production> findAllByFilter(ProductionFilter filter);
}
