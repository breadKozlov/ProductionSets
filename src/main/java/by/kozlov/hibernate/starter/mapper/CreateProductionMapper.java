package by.kozlov.hibernate.starter.mapper;

import by.kozlov.hibernate.starter.dao.SetDao;
import by.kozlov.hibernate.starter.dao.WorkerDao;
import by.kozlov.hibernate.starter.dto.CreateProductionDto;
import by.kozlov.hibernate.starter.entity.Production;
import by.kozlov.hibernate.starter.utils.LocalDateFormatter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CreateProductionMapper implements Mapper<CreateProductionDto, Production> {

    private static final CreateProductionMapper INSTANCE = new CreateProductionMapper();
    private final SetDao setDao = SetDao.getInstance();
    private final WorkerDao workerDao = WorkerDao.getInstance();


    @Override
    public Production mapFrom(CreateProductionDto object) {

        return Production.builder()
                .worker(workerDao.findById(Integer.parseInt(object.getWorker())).orElseThrow())
                .set(setDao.findById(Integer.parseInt(object.getSet())).orElseThrow())
                .madeSets(Integer.parseInt(object.getMadeSets()))
                .dateOfProduction(LocalDateFormatter.format(object.getDateOfProduction()))
                .build();

    }

    public static CreateProductionMapper getInstance() {
        return INSTANCE;
    }
}
