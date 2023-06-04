package by.kozlov.spring.service;

import by.kozlov.spring.dto.CreateProductionDto;
import by.kozlov.spring.dto.UpdateProductionDto;
import by.kozlov.spring.entity.MaterialsProduction;
import by.kozlov.spring.entity.Production;
import by.kozlov.spring.mapper.CreateProductionMapper;
import by.kozlov.spring.mapper.ProductionMapper;
import by.kozlov.spring.mapper.UpdateProductionMapper;
import by.kozlov.spring.repository.ProductionRepository;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import by.kozlov.spring.dto.ProductionDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductionService {
    private final ProductionRepository productionRepository;
    private final ProductionMapper productionMapper;
    private final CreateProductionMapper createProductionMapper;
    private final UpdateProductionMapper updateProductionMapper;

    @Autowired
    public ProductionService(ProductionRepository productionRepository,
                             ProductionMapper productionMapper,
                             CreateProductionMapper createProductionMapper,
                             UpdateProductionMapper updateProductionMapper) {
        this.productionRepository = productionRepository;
        this.productionMapper = productionMapper;
        this.createProductionMapper = createProductionMapper;
        this.updateProductionMapper = updateProductionMapper;
    }

    public List<ProductionDto> findAllByWorkerId(Integer id) {
        return productionRepository.findAllByWorkerId(id).stream()
                    .map(productionMapper::mapFrom).collect(Collectors.toList());
    }

    public List<ProductionDto> findAll() {
        return productionRepository.findAll().stream()
                    .map(productionMapper::mapFrom).collect(Collectors.toList());
    }

    public Optional<ProductionDto> findById(Integer id) {
        return productionRepository.findById(id)
                    .map(productionMapper::mapFrom);
    }

    public Integer create(CreateProductionDto productionDto) {

        try (var validationFactory = Validation.buildDefaultValidatorFactory()) {

            var validator = validationFactory.getValidator();
            var validationResult = validator.validate(productionDto);
            if (!validationResult.isEmpty()) {
                throw new ConstraintViolationException(validationResult);
            }
            var productionEntity = createProductionMapper.mapFrom(productionDto);
            productionEntity = productionRepository.saveAndFlush(productionEntity);
            return productionEntity.getId();
        }
    }

    public boolean delete(Integer id) {

        Optional<Production> maybe;
        maybe = productionRepository.findById(id);
        maybe.ifPresent(it -> productionRepository.delete(maybe.orElseThrow()));
        return maybe.isPresent();
    }


    public void update(UpdateProductionDto productionDto) {

        try (var validationFactory = Validation.buildDefaultValidatorFactory()) {

            var validator = validationFactory.getValidator();
            var validationResult = validator.validate(productionDto);
            if (!validationResult.isEmpty()) {
                throw new ConstraintViolationException(validationResult);
            }
            var productionEntity = updateProductionMapper.mapFrom(productionDto);
            productionRepository.saveAndFlush(productionEntity);
        }
    }

    public List<Object[]> findSumReqMaterials() {

        List<Object[]> sum;
        sum = productionRepository.findSumAllProdSets();
        return sum;
    }
}

