package by.kozlov.hibernate.starter.mapper;

import by.kozlov.hibernate.starter.dao.SetDao;
import by.kozlov.hibernate.starter.dao.WorkerDao;
import by.kozlov.hibernate.starter.dto.UpdateProductionDto;
import by.kozlov.hibernate.starter.entity.Production;
import by.kozlov.hibernate.starter.utils.HibernateUtil;
import by.kozlov.hibernate.starter.utils.LocalDateFormatter;
import lombok.NoArgsConstructor;
import org.hibernate.SessionFactory;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UpdateProductionMapper implements Mapper<UpdateProductionDto, Production> {

    private final SessionFactory sessionFactory = HibernateUtil.getConfig().buildSessionFactory();
    private static final UpdateProductionMapper INSTANCE = new UpdateProductionMapper();
    private final SetDao setDao = SetDao.getInstance();
    private final WorkerDao workerDao = WorkerDao.getInstance();

    @Override
    public Production mapFrom(UpdateProductionDto object) {

        try (var session = sessionFactory.openSession()) {

            Production production;
            session.beginTransaction();
            production = Production.builder()
                    .id(Integer.parseInt(object.getId()))
                    .worker(workerDao.findById(session,Integer.parseInt(object.getWorker())).orElseThrow())
                    .set(setDao.findById(session,Integer.parseInt(object.getSet())).orElseThrow())
                    .madeSets(Integer.parseInt(object.getMadeSets()))
                    .dateOfProduction(LocalDateFormatter.format(object.getDateOfProduction()))
                    .build();
            session.getTransaction().commit();
            return production;
        }

    }

    public static UpdateProductionMapper getInstance() {
        return INSTANCE;
    }
}
