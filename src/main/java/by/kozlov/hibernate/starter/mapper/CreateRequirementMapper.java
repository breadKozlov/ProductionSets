package by.kozlov.hibernate.starter.mapper;

import by.kozlov.hibernate.starter.dao.MaterialDao;
import by.kozlov.hibernate.starter.dao.MaterialRepository;
import by.kozlov.hibernate.starter.dao.SetDao;
import by.kozlov.hibernate.starter.dao.SetRepository;
import by.kozlov.hibernate.starter.dto.CreateRequirementDto;
import by.kozlov.hibernate.starter.entity.Production;
import by.kozlov.hibernate.starter.entity.Requirement;
import by.kozlov.hibernate.starter.utils.HibernateUtil;
import by.kozlov.hibernate.starter.utils.LocalDateFormatter;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;

@RequiredArgsConstructor
public class CreateRequirementMapper implements Mapper<CreateRequirementDto, Requirement> {

    private final SetRepository setRepository;
    private final MaterialRepository materialRepository;
    @Override
    public Requirement mapFrom(CreateRequirementDto object) {
            return Requirement.builder()
                    .set(setRepository.findById(Integer.parseInt(object.getSet())).orElseThrow())
                    .material(materialRepository.findById(Integer.parseInt(object.getMaterial())).orElseThrow())
                    .unitCost(Double.parseDouble(object.getUnitCost()))
                    .totalSets(Integer.parseInt(object.getTotalSets()))
                    .build();

    }
}
