package by.kozlov.hibernate.starter.mapper;

import by.kozlov.hibernate.starter.dao.SetDao;
import by.kozlov.hibernate.starter.dao.WorkerDao;
import by.kozlov.hibernate.starter.dto.CreateProductionDto;
import by.kozlov.hibernate.starter.entity.Production;
import by.kozlov.hibernate.starter.utils.HibernateUtil;
import by.kozlov.hibernate.starter.utils.LocalDateFormatter;
import lombok.NoArgsConstructor;
import org.hibernate.SessionFactory;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CreateProductionMapper implements Mapper<CreateProductionDto, Production> {

    private static final CreateProductionMapper INSTANCE = new CreateProductionMapper();
    private final SetDao setDao = SetDao.getInstance();
    private final WorkerDao workerDao = WorkerDao.getInstance();

    private final SessionFactory sessionFactory = HibernateUtil.getConfig().buildSessionFactory();


    @Override
    public Production mapFrom(CreateProductionDto object) {

        try (var session = sessionFactory.openSession()) {

            Production production;
            session.beginTransaction();
            production = Production.builder()
                .worker(workerDao.findById(session,Integer.parseInt(object.getWorker())).orElseThrow())
                .set(setDao.findById(session,Integer.parseInt(object.getSet())).orElseThrow())
                .madeSets(Integer.parseInt(object.getMadeSets()))
                .dateOfProduction(LocalDateFormatter.format(object.getDateOfProduction()))
                .build();
            session.getTransaction().commit();
            return production;
        }

    }

    public static CreateProductionMapper getInstance() {
        return INSTANCE;
    }
}
