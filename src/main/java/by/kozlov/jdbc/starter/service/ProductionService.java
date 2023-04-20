package by.kozlov.jdbc.starter.service;

import by.kozlov.jdbc.starter.dao.ProductionDao;
import by.kozlov.jdbc.starter.dto.ProductionDto;
import by.kozlov.jdbc.starter.mapper.ProductionMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ProductionService {

    private static final ProductionService INSTANCE = new ProductionService();

    private final ProductionDao productionDao = ProductionDao.getInstance();
    private final ProductionMapper productionMapper = ProductionMapper.getInstance();

    public List<ProductionDto> findAllByWorkerId(Integer id) {
        return productionDao.findAllByWorkerId(id).stream().map(
                productionMapper::mapFrom
        ).collect(Collectors.toList());
    }

    public List<ProductionDto> findAll() {
        return productionDao.findAll().stream().map(
                productionMapper::mapFrom
        ).collect(Collectors.toList());
    }

    private ProductionService() {}

    public static ProductionService getInstance() {
        return INSTANCE;
    }
}
