package by.kozlov.jdbc.starter.service;

import by.kozlov.jdbc.starter.dao.MaterialsProductionDao;
import by.kozlov.jdbc.starter.dto.MaterialsProductionDto;
import by.kozlov.jdbc.starter.dto.ProductionDto;

import java.util.List;
import java.util.stream.Collectors;

public class MaterialsProductionService {

    private static final MaterialsProductionService INSTANCE = new MaterialsProductionService();
    private final MaterialsProductionDao materialsProductionDao = MaterialsProductionDao.getInstance();

    public List<MaterialsProductionDto> findAll() {
        return materialsProductionDao.findAll().stream().map(
                e -> new MaterialsProductionDto(
                        e.getId(),
                        """
                                %s - %s - %s - %s
                                """.formatted(e.getMaterial().getNameOfMaterial(),
                                e.getBrigade().getNameOfBrigade(),
                                e.getQuantity(),e.getDateOfProduction())
                )
        ).collect(Collectors.toList());
    }

    private MaterialsProductionService() {}

    public static MaterialsProductionService getInstance() {
        return INSTANCE;
    }
}
