package by.kozlov.hibernate.starter.mapper;

import by.kozlov.hibernate.starter.dao.MaterialRepository;
import by.kozlov.hibernate.starter.dao.SetRepository;
import by.kozlov.hibernate.starter.dto.UpdateRequirementDto;
import by.kozlov.hibernate.starter.dao.MaterialDao;
import by.kozlov.hibernate.starter.dao.SetDao;
import by.kozlov.hibernate.starter.entity.Production;
import by.kozlov.hibernate.starter.entity.Requirement;
import by.kozlov.hibernate.starter.service.MaterialService;
import by.kozlov.hibernate.starter.service.RequirementService;
import by.kozlov.hibernate.starter.utils.HibernateUtil;
import by.kozlov.hibernate.starter.utils.LocalDateFormatter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;

import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor
public class UpdateRequirementMapper implements Mapper<UpdateRequirementDto, Requirement> {
    private final SetRepository setRepository;
    private final MaterialRepository materialRepository;

    @Override
    public Requirement mapFrom(UpdateRequirementDto object) {

            return Requirement.builder()
                    .id(Integer.parseInt(object.getId()))
                    .set(setRepository.findById(Integer.parseInt(object.getSet())).orElseThrow())
                    .material(materialRepository.findById(Integer.parseInt(object.getMaterial())).orElseThrow())
                    .unitCost(Double.parseDouble(object.getUnitCost()))
                    .totalSets(Integer.parseInt(object.getTotalSets()))
                    .build();
    }
}
