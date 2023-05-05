package by.kozlov.hibernate.starter.service;

import by.kozlov.hibernate.starter.exception.ValidationException;
import by.kozlov.hibernate.starter.validator.CreateProductionValidator;
import by.kozlov.hibernate.starter.validator.UpdateProductionValidator;
import by.kozlov.hibernate.starter.dao.ProductionDao;
import by.kozlov.hibernate.starter.dto.CreateProductionDto;
import by.kozlov.hibernate.starter.dto.ProductionDto;
import by.kozlov.hibernate.starter.dto.UpdateProductionDto;
import by.kozlov.hibernate.starter.mapper.CreateProductionMapper;
import by.kozlov.hibernate.starter.mapper.ProductionMapper;
import by.kozlov.hibernate.starter.mapper.UpdateProductionMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductionService {

    private static final ProductionService INSTANCE = new ProductionService();

    private final ProductionDao productionDao = ProductionDao.getInstance();
    private final ProductionMapper productionMapper = ProductionMapper.getInstance();
    private final CreateProductionValidator createProductionValidator = CreateProductionValidator.getInstance();
    private final CreateProductionMapper createProductionMapper = CreateProductionMapper.getInstance();
    private final UpdateProductionMapper updateProductionMapper = UpdateProductionMapper.getInstance();
    private final UpdateProductionValidator updateProductionValidator = UpdateProductionValidator.getInstance();

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

    public Optional<ProductionDto> findById(Integer id) {
        return productionDao.findById(id).map(productionMapper::mapFrom);
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

    public boolean delete(Integer id) {
        return productionDao.delete(id);
    }

    public boolean update(UpdateProductionDto productionDto) {

        var validationResult = updateProductionValidator.isValid(productionDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
        var productionEntity = updateProductionMapper.mapFrom(productionDto);
        return productionDao.update(productionEntity);
    }

    private ProductionService() {}

    public static ProductionService getInstance() {
        return INSTANCE;
    }
}
