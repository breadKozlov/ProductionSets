package by.kozlov.jdbc.starter.service;

import by.kozlov.jdbc.starter.dao.WorkerDao;
import by.kozlov.jdbc.starter.dao.WorkersSetsDao;
import by.kozlov.jdbc.starter.dto.WorkerDto;
import by.kozlov.jdbc.starter.dto.WorkersSetsDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class WorkersSetsService {

    private static final WorkersSetsService INSTANCE = new WorkersSetsService();
    private static final WorkersSetsDao workersSetsDao = WorkersSetsDao.getInstance();

    public List<WorkersSetsDto> findAllByWorkerId(Integer id) {

        return workersSetsDao.findAllByWorkerId(id).stream().map(
                workersSets -> new WorkersSetsDto(
                        workersSets.getId(),
                        """
                        %s - %s
                        """.formatted(
                                workersSets.getSet().getNameOfSet(),
                                workersSets.getRequirement()
                        )
                )
        ).collect(Collectors.toList());
    }

    public List<WorkersSetsDto> findAll() {
        return workersSetsDao.findAll().stream().map(
                workersSets -> new WorkersSetsDto(
                        workersSets.getId(),
                        """
                           %s - %s - %s
                        """.formatted(
                                workersSets.getWorker(),
                                workersSets.getSet(),
                                workersSets.getRequirement()
                        )
                )
        ).collect(Collectors.toList());
    }

    private WorkersSetsService() {
    }

    public static WorkersSetsService getInstance() {
        return INSTANCE;
    }


}
