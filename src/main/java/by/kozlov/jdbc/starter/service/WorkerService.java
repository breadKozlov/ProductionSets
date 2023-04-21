package by.kozlov.jdbc.starter.service;

import by.kozlov.jdbc.starter.dao.BrigadeDao;
import by.kozlov.jdbc.starter.dao.WorkerDao;
import by.kozlov.jdbc.starter.dto.SetDto;
import by.kozlov.jdbc.starter.dto.WorkerDto;
import by.kozlov.jdbc.starter.entity.Worker;
import by.kozlov.jdbc.starter.mapper.WorkerMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class WorkerService {

    private static final WorkerService INSTANCE = new WorkerService();
    private final WorkerDao workerDao = WorkerDao.getInstance();
    private final WorkerMapper workerMapper = WorkerMapper.getInstance();

    public Optional<WorkerDto> findId(Integer id) {

        return workerDao.findById(id).map(
                workerMapper::mapFrom
        );
    }

    public Optional<WorkerDto> findByEmail(String email) {

        return workerDao.findByEmail(email).map(
                workerMapper::mapFrom
        );
    }

    public List<WorkerDto> findAll() {
        return workerDao.findAll().stream().map(
                workerMapper::mapFrom
        ).collect(Collectors.toList());
    }

    public Optional<WorkerDto> find(String id) {
        return workerDao.findById(Integer.parseInt(id)).map(
                workerMapper::mapFrom
        );
    }


    private WorkerService() {
    }

    public static WorkerService getInstance() {
        return INSTANCE;
    }


}
