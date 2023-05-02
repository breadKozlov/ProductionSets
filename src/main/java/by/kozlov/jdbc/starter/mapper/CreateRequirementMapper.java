package by.kozlov.jdbc.starter.mapper;

import by.kozlov.jdbc.starter.dao.MaterialDao;
import by.kozlov.jdbc.starter.dao.SetDao;
import by.kozlov.jdbc.starter.dto.CreateRequirementDto;
import by.kozlov.jdbc.starter.entity.Requirement;

public class CreateRequirementMapper implements Mapper<CreateRequirementDto, Requirement> {

    private static final CreateRequirementMapper INSTANCE = new CreateRequirementMapper();
    private final SetDao setDao = SetDao.getInstance();
    private final MaterialDao materialDao = MaterialDao.getInstance();


    public static CreateRequirementMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public Requirement mapFrom(CreateRequirementDto object) {
        return Requirement.builder()
                .set(setDao.findById(Integer.parseInt(object.getSet())).orElseThrow())
                .material(materialDao.findById(Integer.parseInt(object.getMaterial())).orElseThrow())
                .unitCost(Double.parseDouble(object.getUnitCost()))
                .totalSets(Integer.parseInt(object.getTotalSets()))
                .build();
    }
}
