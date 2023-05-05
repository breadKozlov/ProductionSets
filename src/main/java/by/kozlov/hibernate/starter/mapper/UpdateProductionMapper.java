package by.kozlov.hibernate.starter.mapper;

import by.kozlov.hibernate.starter.dao.SetDao;
import by.kozlov.hibernate.starter.dao.WorkerDao;
import by.kozlov.hibernate.starter.dto.UpdateProductionDto;
import by.kozlov.hibernate.starter.entity.Production;
import by.kozlov.hibernate.starter.utils.LocalDateFormatter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UpdateProductionMapper implements Mapper<UpdateProductionDto, Production> {

    private static final UpdateProductionMapper INSTANCE = new UpdateProductionMapper();
    private final SetDao setDao = SetDao.getInstance();
    private final WorkerDao workerDao = WorkerDao.getInstance();

    @Override
    public Production mapFrom(UpdateProductionDto object) {

        return Production.builder()
                .id(Integer.parseInt(object.getId()))
                .worker(workerDao.findById(Integer.parseInt(object.getWorker())).orElseThrow())
                .set(setDao.findById(Integer.parseInt(object.getSet())).orElseThrow())
                .madeSets(Integer.parseInt(object.getMadeSets()))
                .dateOfProduction(LocalDateFormatter.format(object.getDateOfProduction()))
                .build();

    }

    public static UpdateProductionMapper getInstance() {
        return INSTANCE;
    }
}
