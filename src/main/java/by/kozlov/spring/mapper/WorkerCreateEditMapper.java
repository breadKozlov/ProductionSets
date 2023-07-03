package by.kozlov.spring.mapper;

import by.kozlov.spring.database.entity.Brigade;
import by.kozlov.spring.database.entity.Worker;
import by.kozlov.spring.database.repository.BrigadeRepository;
import by.kozlov.spring.dto.WorkerCreateEditDto;
import lombok.RequiredArgsConstructor;
import org.hibernate.jdbc.Work;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class WorkerCreateEditMapper implements Mapper<WorkerCreateEditDto,Worker> {

    private final BrigadeRepository brigadeRepository;
    @Override
    public Worker map(WorkerCreateEditDto object) {
        Worker worker = new Worker();
        copy(object,worker);
        return worker;
    }

    @Override
    public Worker map(WorkerCreateEditDto fromObject, Worker toObject) {
        copy(fromObject,toObject);
        return toObject;
    }

    @Override
    public void copy(WorkerCreateEditDto fromObject, Worker toObject) {
        toObject.setNameOfWorker(fromObject.getNameOfWorker());
        toObject.setSurnameOfWorker(fromObject.getSurnameOfWorker());
        toObject.setSpeciality(fromObject.getSpeciality());
        toObject.setRank(fromObject.getRank());
        toObject.setExperience(fromObject.getExperience());
        toObject.setBrigade(getBrigade(fromObject.getBrigadeId()));
        toObject.setEmail(fromObject.getEmail());
    }

    private Brigade getBrigade(Integer brigadeId) {
        return Optional.ofNullable(brigadeId)
                .flatMap(brigadeRepository::findById)
                .orElse(null);
    }
}
