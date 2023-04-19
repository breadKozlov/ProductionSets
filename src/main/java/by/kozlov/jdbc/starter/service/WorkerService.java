package by.kozlov.jdbc.starter.service;

import by.kozlov.jdbc.starter.dao.BrigadeDao;
import by.kozlov.jdbc.starter.dao.WorkerDao;
import by.kozlov.jdbc.starter.dto.WorkerDto;
import by.kozlov.jdbc.starter.entity.Worker;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class WorkerService {

    private static final WorkerService INSTANCE = new WorkerService();
    private final WorkerDao workerDao = WorkerDao.getInstance();

    public List<WorkerDto> findId() {

        return workerDao.findAll().stream().map(
                worker -> new WorkerDto(
                        worker.getId(),
                        """
                        %s - %s - %s
                        """.formatted(
                                worker.getNameOfWorker(),
                                worker.getSurnameOfWorker(),
                                worker.getBrigade().getNameOfBrigade()
                        )
                )
        ).collect(Collectors.toList());
    }

    public Optional<WorkerDto> findByEmail(String email) {

        return workerDao.findByEmail(email).map(
                worker -> new WorkerDto(
                        worker.getId(),
                        """
                        %s - %s - %s
                        """.formatted(
                                worker.getNameOfWorker(),
                                worker.getSurnameOfWorker(),
                                worker.getBrigade().getNameOfBrigade()
                        )
                )
        );
    }

    public List<WorkerDto> findAll() {
        return workerDao.findAll().stream().map(
                worker -> new WorkerDto(
                        worker.getId(),
                        """
                           %s - %s - %s
                        """.formatted(
                                worker.getNameOfWorker(),
                                worker.getSurnameOfWorker(),
                                worker.getBrigade().getNameOfBrigade()
                        )
                )
        ).collect(Collectors.toList());
    }

    private WorkerService() {
    }

    public static WorkerService getInstance() {
        return INSTANCE;
    }


}
