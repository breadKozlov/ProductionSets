package by.kozlov.hibernate.starter.mapper;

import by.kozlov.hibernate.starter.dao.BrigadeDao;
import by.kozlov.hibernate.starter.dao.MaterialDao;
import by.kozlov.hibernate.starter.dto.UpdateMaterialsProductionDto;
import by.kozlov.hibernate.starter.entity.MaterialsProduction;
import by.kozlov.hibernate.starter.utils.HibernateUtil;
import by.kozlov.hibernate.starter.utils.LocalDateFormatter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.SessionFactory;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateMaterialsProductionMapper implements Mapper<UpdateMaterialsProductionDto, MaterialsProduction> {

    private final SessionFactory sessionFactory = HibernateUtil.getConfig().buildSessionFactory();
    private static final UpdateMaterialsProductionMapper INSTANCE = new UpdateMaterialsProductionMapper();
    private final MaterialDao materialDao = MaterialDao.getInstance();
    private final BrigadeDao brigadeDao = BrigadeDao.getInstance();

    @Override
    public MaterialsProduction mapFrom(UpdateMaterialsProductionDto object) {
        try (var session = sessionFactory.openSession()) {
            MaterialsProduction production;
            session.beginTransaction();
            production = MaterialsProduction.builder()
                    .id(Integer.parseInt(object.getId()))
                    .material(materialDao.findById(session,Integer.parseInt(object.getMaterial())).orElseThrow())
                    .brigade(brigadeDao.findById(session,Integer.parseInt(object.getBrigade())).orElseThrow())
                    .quantity(Double.parseDouble(object.getQuantity()))
                    .dateOfProduction(LocalDateFormatter.format(object.getDateOfProduction()))
                    .build();
            session.getTransaction().commit();
            return production;
        }
    }

    public static UpdateMaterialsProductionMapper getInstance() {
        return INSTANCE;
    }
}
