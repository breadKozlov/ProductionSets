package by.kozlov.hibernate.starter.mapper;

import by.kozlov.hibernate.starter.dto.WorkerDto;
import by.kozlov.hibernate.starter.entity.Worker;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor
public class WorkerMapper implements Mapper<Worker, WorkerDto> {

    private final BrigadeMapper brigadeMapper;

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
