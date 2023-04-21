package by.kozlov.jdbc.starter.mapper;

import by.kozlov.jdbc.starter.dao.SetDao;
import by.kozlov.jdbc.starter.dao.WorkerDao;
import by.kozlov.jdbc.starter.dto.CreateProductionDto;
import by.kozlov.jdbc.starter.entity.Gender;
import by.kozlov.jdbc.starter.entity.Production;
import by.kozlov.jdbc.starter.entity.Role;
import by.kozlov.jdbc.starter.entity.User;
import by.kozlov.jdbc.starter.utils.LocalDateFormatter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
