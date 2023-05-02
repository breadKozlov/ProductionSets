package by.kozlov.jdbc.starter.service;

import by.kozlov.jdbc.starter.dao.MaterialsProductionDao;
import by.kozlov.jdbc.starter.dto.MaterialsProductionDto;
import by.kozlov.jdbc.starter.dto.ProductionDto;
import by.kozlov.jdbc.starter.mapper.MaterialsProductionMapper;

import java.util.List;
import java.util.stream.Collectors;

public class MaterialsProductionService {

    private static final MaterialsProductionService INSTANCE = new MaterialsProductionService();
    private final MaterialsProductionDao materialsProductionDao = MaterialsProductionDao.getInstance();
    private final MaterialsProductionMapper materialsProductionMapper = MaterialsProductionMapper.getInstance();

    public List<MaterialsProductionDto> findAll() {
        return materialsProductionDao.findAll().stream()
                .map(materialsProductionMapper::mapFrom).collect(Collectors.toList());
    }

    private MaterialsProductionService() {}

    public static MaterialsProductionService getInstance() {
        return INSTANCE;
    }
}
