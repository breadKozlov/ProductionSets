package by.kozlov.jdbc.starter.service;

import by.kozlov.jdbc.starter.dao.WorkerDao;
import by.kozlov.jdbc.starter.dao.WorkersSetsDao;
import by.kozlov.jdbc.starter.dto.CreateRequirementDto;
import by.kozlov.jdbc.starter.dto.CreateWorkersSetsDto;
import by.kozlov.jdbc.starter.dto.WorkerDto;
import by.kozlov.jdbc.starter.dto.WorkersSetsDto;
import by.kozlov.jdbc.starter.exception.ValidationException;
import by.kozlov.jdbc.starter.mapper.CreateWorkersSetsMapper;
import by.kozlov.jdbc.starter.mapper.WorkersSetsMapper;
import by.kozlov.jdbc.starter.validator.CreateWorkersSetsValidator;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class WorkersSetsService {

    private static final WorkersSetsService INSTANCE = new WorkersSetsService();
    private final WorkersSetsDao workersSetsDao = WorkersSetsDao.getInstance();
    private final WorkersSetsMapper workersSetsMapper = WorkersSetsMapper.getInstance();

    private final CreateWorkersSetsValidator createWorkersSetsValidator = CreateWorkersSetsValidator.getInstance();
    private final CreateWorkersSetsMapper createWorkersSetsMapper = CreateWorkersSetsMapper.getInstance();

    public List<WorkersSetsDto> findAllByWorkerId(Integer id) {

        return workersSetsDao.findAllByWorkerId(id).stream().map(
                workersSetsMapper::mapFrom
                ).collect(Collectors.toList());
    }

    public List<WorkersSetsDto> findAll() {
        return workersSetsDao.findAll().stream().map(
                workersSetsMapper::mapFrom
        ).collect(Collectors.toList());
    }

    public Integer create(CreateWorkersSetsDto workersSetsDto) {
        var validationResult = createWorkersSetsValidator.isValid(workersSetsDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
        var requirementEntity = createWorkersSetsMapper.mapFrom(workersSetsDto);
        workersSetsDao.save(requirementEntity);
        return requirementEntity.getId();
    }

    public boolean delete(Integer id) {
        return workersSetsDao.delete(id);
    }

    private WorkersSetsService() {
    }

    public static WorkersSetsService getInstance() {
        return INSTANCE;
    }


}
