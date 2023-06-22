package by.kozlov.spring.mapper;

import by.kozlov.spring.database.entity.Production;
import by.kozlov.spring.database.entity.Set;
import by.kozlov.spring.database.entity.Worker;
import by.kozlov.spring.database.repository.SetRepository;
import by.kozlov.spring.database.repository.WorkerRepository;
import by.kozlov.spring.dto.ProductionCreateEditDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductionCreateEditMapper implements Mapper<ProductionCreateEditDto, Production> {

    private final SetRepository setRepository;
    private final WorkerRepository workerRepository;
    private Worker getWorker(Integer workerId) {
        return  Optional.ofNullable(workerId)
                .flatMap(workerRepository::findById)
                .orElse(null);
    }

    private Set getSet(Integer setId) {
        return Optional.ofNullable(setId)
                .flatMap(setRepository::findById)
                .orElse(null);
    }

    @Override
    public Production map(ProductionCreateEditDto object) {
        Production production = new Production();
        copy(object,production);
        return production;
    }

    @Override
    public Production map(ProductionCreateEditDto fromObject, Production toObject) {
        copy(fromObject,toObject);
        return toObject;
    }

    @Override
    public void copy(ProductionCreateEditDto fromObject, Production toObject) {
        toObject.setWorker(getWorker(fromObject.getWorkerId()));
        toObject.setSet(getSet(fromObject.getSetId()));
        toObject.setMadeSets(fromObject.getMadeSets());
        toObject.setDateOfProduction(fromObject.getDateOfProduction());
    }
}
