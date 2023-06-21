package by.kozlov.spring.mapper;

import by.kozlov.spring.dto.WorkerDto;
import by.kozlov.spring.database.entity.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WorkerMapper implements OlderMapper<Worker, WorkerDto> {

    private final BrigadeMapper brigadeMapper;

    @Autowired
    public WorkerMapper(BrigadeMapper brigadeMapper) {
        this.brigadeMapper = brigadeMapper;
    }

    @Override
    public WorkerDto mapFrom(Worker object) {
        return WorkerDto.builder()
                .id(object.getId())
                .nameOfWorker(object.getNameOfWorker())
                .surnameOfWorker(object.getSurnameOfWorker())
                .speciality(object.getSpeciality())
                .rank(object.getRank())
                .experience(object.getExperience())
                .brigade(brigadeMapper.mapFrom(object.getBrigade()))
                .email(object.getEmail())
                .build();
    }
}
