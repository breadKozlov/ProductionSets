package by.kozlov.jdbc.starter.service;

import by.kozlov.jdbc.starter.dao.ProductionDao;
import by.kozlov.jdbc.starter.dto.CreateProductionDto;
import by.kozlov.jdbc.starter.dto.CreateUserDto;
import by.kozlov.jdbc.starter.dto.ProductionDto;
import by.kozlov.jdbc.starter.exception.ValidationException;
import by.kozlov.jdbc.starter.mapper.CreateProductionMapper;
import by.kozlov.jdbc.starter.mapper.ProductionMapper;
import by.kozlov.jdbc.starter.validator.CreateProductionValidator;

import java.util.List;
import java.util.stream.Collectors;

public class ProductionService {

    private static final ProductionService INSTANCE = new ProductionService();

    private final ProductionDao productionDao = ProductionDao.getInstance();
    private final ProductionMapper productionMapper = ProductionMapper.getInstance();
    private final CreateProductionValidator createProductionValidator = CreateProductionValidator.getInstance();
    private final CreateProductionMapper createProductionMapper = CreateProductionMapper.getInstance();

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

    public Integer create(CreateProductionDto productionDto) {
        var validationResult = createProductionValidator.isValid(productionDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
        var productionEntity = createProductionMapper.mapFrom(productionDto);
        productionDao.save(productionEntity);
        return productionEntity.getId();
    }

    private ProductionService() {}

    public static ProductionService getInstance() {
        return INSTANCE;
    }
}
