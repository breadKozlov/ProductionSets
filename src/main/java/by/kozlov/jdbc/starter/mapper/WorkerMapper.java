package by.kozlov.jdbc.starter.mapper;

import by.kozlov.jdbc.starter.dao.WorkerDao;
import by.kozlov.jdbc.starter.dto.WorkerDto;
import by.kozlov.jdbc.starter.entity.Worker;
import lombok.AccessLevel;
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
