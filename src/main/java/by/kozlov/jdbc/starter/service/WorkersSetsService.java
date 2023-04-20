package by.kozlov.jdbc.starter.service;

import by.kozlov.jdbc.starter.dao.WorkerDao;
import by.kozlov.jdbc.starter.dao.WorkersSetsDao;
import by.kozlov.jdbc.starter.dto.WorkerDto;
import by.kozlov.jdbc.starter.dto.WorkersSetsDto;
import by.kozlov.jdbc.starter.mapper.WorkersSetsMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class WorkersSetsService {

    private static final WorkersSetsService INSTANCE = new WorkersSetsService();
    private final WorkersSetsDao workersSetsDao = WorkersSetsDao.getInstance();
    private final WorkersSetsMapper workersSetsMapper = WorkersSetsMapper.getInstance();

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

    private WorkersSetsService() {
    }

    public static WorkersSetsService getInstance() {
        return INSTANCE;
    }


}
