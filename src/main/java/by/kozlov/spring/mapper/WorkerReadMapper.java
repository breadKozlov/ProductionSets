package by.kozlov.spring.mapper;

import by.kozlov.spring.database.entity.Worker;
import by.kozlov.spring.dto.BrigadeReadDto;
import by.kozlov.spring.dto.WorkerReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class WorkerReadMapper implements Mapper<Worker, WorkerReadDto> {

    private final BrigadeReadMapper brigadeReadMapper;

    @Override
    public WorkerReadDto map(Worker object) {

        BrigadeReadDto brigadeReadDto = Optional.ofNullable(object.getBrigade())
                .map(brigadeReadMapper::map)
                .orElse(null);

        return WorkerReadDto.builder()
                .id(object.getId())
                .nameOfWorker(object.getNameOfWorker())
                .surnameOfWorker(object.getSurnameOfWorker())
                .speciality(object.getSpeciality())
                .rank(object.getRank())
                .experience(object.getExperience())
                .brigade(brigadeReadDto)
                .email(object.getEmail())
                .build();
    }
}
