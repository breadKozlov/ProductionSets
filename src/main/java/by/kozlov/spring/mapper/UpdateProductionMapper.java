package by.kozlov.spring.mapper;

import by.kozlov.spring.dto.UpdateProductionDto;
import by.kozlov.spring.database.entity.Production;
import by.kozlov.spring.database.repository.SetRepository;
import by.kozlov.spring.database.repository.WorkerRepository;
import by.kozlov.spring.utils.LocalDateFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateProductionMapper implements OlderMapper<UpdateProductionDto, Production> {

    private final SetRepository setRepository;
    private final WorkerRepository workerRepository;

    @Autowired
    public UpdateProductionMapper(SetRepository setRepository, WorkerRepository workerRepository) {
        this.setRepository = setRepository;
        this.workerRepository = workerRepository;
    }

    @Override
    public Production mapFrom(UpdateProductionDto object) {

        return Production.builder()
                .id(Integer.parseInt(object.getId()))
                .worker(workerRepository.findById(Integer.parseInt(object.getWorker())).orElseThrow())
                .set(setRepository.findById(Integer.parseInt(object.getSet())).orElseThrow())
                .madeSets(Integer.parseInt(object.getMadeSets()))
                .dateOfProduction(LocalDateFormatter.format(object.getDateOfProduction()))
                .build();
    }
}
