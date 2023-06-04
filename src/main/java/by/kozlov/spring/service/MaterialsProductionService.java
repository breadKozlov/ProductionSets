package by.kozlov.spring.service;

import by.kozlov.spring.dto.CreateMaterialsProductionDto;
import by.kozlov.spring.dto.MaterialsProductionDto;
import by.kozlov.spring.entity.MaterialsProduction;
import by.kozlov.spring.mapper.CreateMaterialsProductionMapper;
import by.kozlov.spring.mapper.MaterialsProductionMapper;
import by.kozlov.spring.mapper.UpdateMaterialsProductionMapper;
import by.kozlov.spring.repository.MaterialsProductionRepository;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import by.kozlov.spring.dto.UpdateMaterialsProductionDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MaterialsProductionService {

    private final MaterialsProductionRepository materialProductionRepository;

    private final MaterialsProductionMapper materialsProductionMapper;

    private final CreateMaterialsProductionMapper createMaterialsProductionMapper;

    private final UpdateMaterialsProductionMapper updateMaterialsProductionMapper;

    @Autowired
    public MaterialsProductionService(MaterialsProductionRepository materialProductionRepository,
                                      MaterialsProductionMapper materialsProductionMapper,
                                      CreateMaterialsProductionMapper createMaterialsProductionMapper,
                                      UpdateMaterialsProductionMapper updateMaterialsProductionMapper) {
        this.materialProductionRepository = materialProductionRepository;
        this.materialsProductionMapper = materialsProductionMapper;
        this.createMaterialsProductionMapper = createMaterialsProductionMapper;
        this.updateMaterialsProductionMapper = updateMaterialsProductionMapper;
    }

    public List<MaterialsProductionDto> findAll() {
        return materialProductionRepository.findAll().stream()
                .map(materialsProductionMapper::mapFrom).collect(Collectors.toList());
    }

    public Optional<MaterialsProductionDto> findById(Integer id) {

        return materialProductionRepository.findById(id)
                .map(materialsProductionMapper::mapFrom);
    }

    public List<MaterialsProductionDto> findAllByBrigadeId(Integer id) {
        return materialProductionRepository.findAllByBrigadeId(id).stream()
                .map(materialsProductionMapper::mapFrom).collect(Collectors.toList());
    }

    public Integer create(CreateMaterialsProductionDto materialsProductionDto) {
        try (var validationFactory = Validation.buildDefaultValidatorFactory()) {

            var validator = validationFactory.getValidator();
            var validationResult = validator.validate(materialsProductionDto);
            if (!validationResult.isEmpty()) {
                throw new ConstraintViolationException(validationResult);
            }
            var productionEntity = createMaterialsProductionMapper.mapFrom(materialsProductionDto);
            productionEntity = materialProductionRepository.saveAndFlush(productionEntity);
            return productionEntity.getId();
        }
    }

    public boolean delete(Integer id) {

        Optional<MaterialsProduction> maybe;
        maybe = materialProductionRepository.findById(id);
        maybe.ifPresent(it -> materialProductionRepository.delete(maybe.orElseThrow()));
        return maybe.isPresent();
    }

    public void update(UpdateMaterialsProductionDto productionDto) {

        try (var validationFactory = Validation.buildDefaultValidatorFactory()) {

            var validator = validationFactory.getValidator();
            var validationResult = validator.validate(productionDto);
            if (!validationResult.isEmpty()) {
                throw new ConstraintViolationException(validationResult);
            }
            var productionEntity = updateMaterialsProductionMapper.mapFrom(productionDto);
            materialProductionRepository.saveAndFlush(productionEntity);
        }
    }

}
