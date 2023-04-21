package by.kozlov.jdbc.starter.mapper;

import by.kozlov.jdbc.starter.dao.SetDao;
import by.kozlov.jdbc.starter.dao.WorkerDao;
import by.kozlov.jdbc.starter.dto.CreateProductionDto;
import by.kozlov.jdbc.starter.dto.UpdateProductionDto;
import by.kozlov.jdbc.starter.entity.Production;
import by.kozlov.jdbc.starter.utils.LocalDateFormatter;
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
