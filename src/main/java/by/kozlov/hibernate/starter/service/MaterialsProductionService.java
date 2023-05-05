package by.kozlov.hibernate.starter.service;

import by.kozlov.hibernate.starter.exception.ValidationException;
import by.kozlov.hibernate.starter.dao.MaterialsProductionDao;
import by.kozlov.hibernate.starter.dto.CreateMaterialsProductionDto;
import by.kozlov.hibernate.starter.dto.MaterialsProductionDto;
import by.kozlov.hibernate.starter.mapper.CreateMaterialsProductionMapper;
import by.kozlov.hibernate.starter.mapper.MaterialsProductionMapper;
import by.kozlov.hibernate.starter.validator.CreateMaterialsProductionValidator;

import java.util.List;
import java.util.stream.Collectors;

public class MaterialsProductionService {

    private static final MaterialsProductionService INSTANCE = new MaterialsProductionService();
    private final MaterialsProductionDao materialsProductionDao = MaterialsProductionDao.getInstance();
    private final MaterialsProductionMapper materialsProductionMapper = MaterialsProductionMapper.getInstance();

    private final CreateMaterialsProductionMapper createMaterialsProductionMapper = CreateMaterialsProductionMapper.getInstance();

    private final CreateMaterialsProductionValidator createMaterialsProductionValidator = CreateMaterialsProductionValidator.getInstance();
    public List<MaterialsProductionDto> findAll() {
        return materialsProductionDao.findAll().stream()
                .map(materialsProductionMapper::mapFrom).collect(Collectors.toList());
    }

    public List<MaterialsProductionDto> findAllByBrigadeId(Integer id) {
        return materialsProductionDao.findAllByBrigadeId(id).stream()
                .map(materialsProductionMapper::mapFrom).collect(Collectors.toList());
    }

    public Integer create(CreateMaterialsProductionDto materialsProductionDto) {
        var validationResult = createMaterialsProductionValidator.isValid(materialsProductionDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
        var productionEntity = createMaterialsProductionMapper.mapFrom(materialsProductionDto);
        materialsProductionDao.save(productionEntity);
        return productionEntity.getId();
    }

    public boolean delete(Integer id) {
        return materialsProductionDao.delete(id);
    }

    private MaterialsProductionService() {}

    public static MaterialsProductionService getInstance() {
        return INSTANCE;
    }
}
