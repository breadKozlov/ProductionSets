package by.kozlov.hibernate.starter.mapper;

import by.kozlov.hibernate.starter.dao.BrigadeDao;
import by.kozlov.hibernate.starter.dao.MaterialDao;
import by.kozlov.hibernate.starter.dao.MaterialsProductionDao;
import by.kozlov.hibernate.starter.entity.Material;
import by.kozlov.hibernate.starter.entity.MaterialsProduction;
import by.kozlov.hibernate.starter.entity.Production;
import by.kozlov.hibernate.starter.utils.HibernateUtil;
import by.kozlov.hibernate.starter.utils.LocalDateFormatter;
import by.kozlov.hibernate.starter.dto.CreateMaterialsProductionDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.SessionFactory;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateMaterialsProductionMapper implements Mapper<CreateMaterialsProductionDto, MaterialsProduction>{

    private final SessionFactory sessionFactory = HibernateUtil.getConfig().buildSessionFactory();
    private static final CreateMaterialsProductionMapper INSTANCE = new CreateMaterialsProductionMapper();

    private final MaterialDao materialDao = MaterialDao.getInstance();
    private final BrigadeDao brigadeDao = BrigadeDao.getInstance();

    @Override
    public MaterialsProduction mapFrom(CreateMaterialsProductionDto object) {
        try (var session = sessionFactory.openSession()) {

            MaterialsProduction production;
            session.beginTransaction();
            production = MaterialsProduction.builder()
                    .material(materialDao.findById(session,Integer.parseInt(object.getMaterial())).orElseThrow())
                    .brigade(brigadeDao.findById(session,Integer.parseInt(object.getBrigade())).orElseThrow())
                    .quantity(Double.parseDouble(object.getQuantity()))
                    .dateOfProduction(LocalDateFormatter.format(object.getDateOfProduction()))
                    .build();
            session.getTransaction().commit();
            return production;
        }
    }

    public static CreateMaterialsProductionMapper getInstance() {
        return INSTANCE;
    }
}
