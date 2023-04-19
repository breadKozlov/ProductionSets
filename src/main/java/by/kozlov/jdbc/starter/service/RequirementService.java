package by.kozlov.jdbc.starter.service;

import by.kozlov.jdbc.starter.dao.RequirementDao;
import by.kozlov.jdbc.starter.dto.ProductionDto;
import by.kozlov.jdbc.starter.dto.RequirementDto;

import java.util.List;
import java.util.stream.Collectors;

public class RequirementService {

    private static final RequirementService INSTANCE = new RequirementService();
    private static RequirementDao requirementDao = RequirementDao.getInstance();

    public List<RequirementDto> findAll() {
        return requirementDao.findAll().stream().map(
                e -> new RequirementDto(
                        e.getId(),
                        """
                                %s - %s - %s - %s - %s
                                """.formatted(e.getSet().getNameOfSet(),
                                e.getMaterial().getNameOfMaterial(),
                                e.getUnitCost(),e.getTotalSets(),
                                e.getUnitCost() * e.getTotalSets())
                )
        ).collect(Collectors.toList());
    }

    private RequirementService() {}

    public static RequirementService getInstance() {
        return INSTANCE;
    }
}
