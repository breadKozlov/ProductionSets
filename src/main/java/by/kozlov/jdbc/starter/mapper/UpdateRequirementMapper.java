package by.kozlov.jdbc.starter.mapper;

import by.kozlov.jdbc.starter.dao.MaterialDao;
import by.kozlov.jdbc.starter.dao.SetDao;
import by.kozlov.jdbc.starter.dto.UpdateProductionDto;
import by.kozlov.jdbc.starter.dto.UpdateRequirementDto;
import by.kozlov.jdbc.starter.entity.Requirement;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UpdateRequirementMapper implements Mapper<UpdateRequirementDto, Requirement> {

    private static final UpdateRequirementMapper INSTANCE = new UpdateRequirementMapper();

    private final SetDao setDao = SetDao.getInstance();
    private final MaterialDao materialDao = MaterialDao.getInstance();

    @Override
    public Requirement mapFrom(UpdateRequirementDto object) {
        return Requirement.builder()
                .set(setDao.findById(Integer.parseInt(object.getSet())).orElseThrow())
                .material(materialDao.findById(Integer.parseInt(object.getMaterial())).orElseThrow())
                .unitCost(Double.parseDouble(object.getUnitCost()))
                .totalSets(Integer.parseInt(object.getTotalSets()))
                .build();
    }

    public static UpdateRequirementMapper getInstance() {
        return INSTANCE;
    }
}
