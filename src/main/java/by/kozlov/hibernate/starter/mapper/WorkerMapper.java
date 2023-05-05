package by.kozlov.hibernate.starter.mapper;

import by.kozlov.hibernate.starter.dto.WorkerDto;
import by.kozlov.hibernate.starter.entity.Worker;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class WorkerMapper implements Mapper<Worker, WorkerDto> {

    private static final WorkerMapper INSTANCE = new WorkerMapper();

    @Override
    public WorkerDto mapFrom(Worker object) {
        return WorkerDto.builder()
                .id(object.getId())
                .nameOfWorker(object.getNameOfWorker())
                .surnameOfWorker(object.getSurnameOfWorker())
                .speciality(object.getSpeciality())
                .rank(object.getRank())
                .experience(object.getExperience())
                .brigade(object.getBrigade())
                .email(object.getEmail())
                .build();
    }

    public static WorkerMapper getInstance() {
        return INSTANCE;
    }
}
