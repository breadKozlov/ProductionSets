package by.kozlov.hibernate.starter.mapper;

import by.kozlov.hibernate.starter.dao.MaterialDao;
import by.kozlov.hibernate.starter.dao.SetDao;
import by.kozlov.hibernate.starter.dto.CreateRequirementDto;
import by.kozlov.hibernate.starter.entity.Production;
import by.kozlov.hibernate.starter.entity.Requirement;
import by.kozlov.hibernate.starter.utils.HibernateUtil;
import by.kozlov.hibernate.starter.utils.LocalDateFormatter;
import org.hibernate.SessionFactory;

public class CreateRequirementMapper implements Mapper<CreateRequirementDto, Requirement> {

    private static final CreateRequirementMapper INSTANCE = new CreateRequirementMapper();
    private final SetDao setDao = SetDao.getInstance();
    private final MaterialDao materialDao = MaterialDao.getInstance();

    private final SessionFactory sessionFactory = HibernateUtil.getConfig().buildSessionFactory();


    public static CreateRequirementMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public Requirement mapFrom(CreateRequirementDto object) {

        try (var session = sessionFactory.openSession()) {

            Requirement requirement;
            session.beginTransaction();
            requirement = Requirement.builder()
                    .set(setDao.findById(session,Integer.parseInt(object.getSet())).orElseThrow())
                    .material(materialDao.findById(session,Integer.parseInt(object.getMaterial())).orElseThrow())
                    .unitCost(Double.parseDouble(object.getUnitCost()))
                    .totalSets(Integer.parseInt(object.getTotalSets()))
                    .build();
            session.getTransaction().commit();
            return requirement;
        }

    }
}
