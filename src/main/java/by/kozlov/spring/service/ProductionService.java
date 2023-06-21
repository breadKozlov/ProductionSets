package by.kozlov.spring.service;

import by.kozlov.spring.dto.*;
import by.kozlov.spring.database.entity.Production;
import by.kozlov.spring.mapper.*;
import by.kozlov.spring.database.repository.ProductionRepository;
import jakarta.persistence.Id;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductionService {
    private final ProductionRepository productionRepository;
    private final ProductionReadMapper productionReadMapper;
    private final ProductionCreateEditMapper productionCreateEditMapper;

    public List<ProductionReadDto> findAllByWorkerId(Integer id, Sort sort) {
        return productionRepository.findAllByWorkerId(id,sort).stream()
                    .map(productionReadMapper::map).toList();
    }

    public List<ProductionReadDto> findAll(Pageable page) {
        return productionRepository.findAll(page).stream()
                    .map(productionReadMapper::map).toList();
    }

    public Optional<ProductionReadDto> findById(Integer id) {
        return productionRepository.findById(id)
                    .map(productionReadMapper::map);
    }

    @Transactional
    public ProductionReadDto create(ProductionCreateEditDto productionDto) {

        try (var validationFactory = Validation.buildDefaultValidatorFactory()) {

            var validator = validationFactory.getValidator();
            var validationResult = validator.validate(productionDto);
            if (!validationResult.isEmpty()) {
                throw new ConstraintViolationException(validationResult);
            }
            return Optional.of(productionDto)
                    .map(productionCreateEditMapper::map)
                    .map(productionRepository::save)
                    .map(productionReadMapper::map)
                    .orElseThrow();
        }
    }

    public boolean delete(Integer id) {
        return productionRepository.findById(id)
                .map(entity -> {
                    productionRepository.delete(entity);
                    productionRepository.flush();
                    return true;
                }).orElse(false);
    }


    public Optional<ProductionReadDto> update(Integer id, ProductionCreateEditDto productionDto) {

        try (var validationFactory = Validation.buildDefaultValidatorFactory()) {
            var validator = validationFactory.getValidator();
            var validationResult = validator.validate(productionDto);
            if (!validationResult.isEmpty()) {
                throw new ConstraintViolationException(validationResult);
            }
            return productionRepository.findById(id)
                    .map(entity -> productionCreateEditMapper.map(productionDto,entity))
                    .map(productionRepository::saveAndFlush)
                    .map(productionReadMapper::map);
        }
    }

    public List<Object[]> findSumReqMaterials() {

        List<Object[]> sum;
        sum = productionRepository.findSumAllProdSets();
        return sum;
    }
}

