package by.kozlov.jdbc.starter.service;

import by.kozlov.jdbc.starter.dao.ProductionDao;
import by.kozlov.jdbc.starter.dto.ProductionDto;

import java.util.List;
import java.util.stream.Collectors;

public class ProductionService {

    private static final ProductionService INSTANCE = new ProductionService();

    private final ProductionDao productionDao = ProductionDao.getInstance();

    public List<ProductionDto> findAllByWorkerId(Integer id) {
        return productionDao.findAllByWorkerId(id).stream().map(
                e -> new ProductionDto(
                        e.getId(),
                        e.getWorker().getId(),
                        e.getSet().getNameOfSet(),
                        e.getMadeSets(),
                        e.getDateOfProduction()
                )
        ).collect(Collectors.toList());
    }

    private ProductionService() {}

    public static ProductionService getInstance() {
        return INSTANCE;
    }
}
