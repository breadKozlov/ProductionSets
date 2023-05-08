package by.kozlov.hibernate.starter.mapper;

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
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UpdateRequirementMapper implements Mapper<UpdateRequirementDto, Requirement> {

    private final SessionFactory sessionFactory = HibernateUtil.getConfig().buildSessionFactory();
    private static final UpdateRequirementMapper INSTANCE = new UpdateRequirementMapper();
    private final SetDao setDao = SetDao.getInstance();
    private final MaterialDao materialDao = MaterialDao.getInstance();

    @Override
    public Requirement mapFrom(UpdateRequirementDto object) {

        try (var session = sessionFactory.openSession()) {

            Requirement requirement;
            session.beginTransaction();
            requirement = Requirement.builder()
                    .id(Integer.parseInt(object.getId()))
                    .set(setDao.findById(session,Integer.parseInt(object.getSet())).orElseThrow())
                    .material(materialDao.findById(session,Integer.parseInt(object.getMaterial())).orElseThrow())
                    .unitCost(Double.parseDouble(object.getUnitCost()))
                    .totalSets(Integer.parseInt(object.getTotalSets()))
                    .build();
            session.getTransaction().commit();
            return requirement;
        }
    }

    public static UpdateRequirementMapper getInstance() {
        return INSTANCE;
    }
}
