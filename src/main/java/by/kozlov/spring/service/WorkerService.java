package by.kozlov.spring.service;

import by.kozlov.spring.dto.WorkerDto;
import by.kozlov.spring.mapper.WorkerMapper;
import by.kozlov.spring.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorkerService {

    private final WorkerMapper workerMapper;
    private final WorkerRepository workerRepository;

    @Autowired
    public WorkerService(WorkerMapper workerMapper, WorkerRepository workerRepository) {
        this.workerMapper = workerMapper;
        this.workerRepository = workerRepository;
    }

    public Optional<WorkerDto> findById(Integer id) {
        return workerRepository.findById(id).map(workerMapper::mapFrom);
    }

    public Optional<WorkerDto> findByEmail(String email) {
        return workerRepository.findByEmail(email).map(workerMapper::mapFrom);
    }

    public List<WorkerDto> findAll() {

        return workerRepository.findAll().stream()
                .map(workerMapper::mapFrom).collect(Collectors.toList());
    }
}
