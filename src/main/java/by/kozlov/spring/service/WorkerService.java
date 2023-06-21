package by.kozlov.spring.service;

import by.kozlov.spring.database.repository.WorkerRepository;
import by.kozlov.spring.dto.WorkerReadDto;
import by.kozlov.spring.mapper.WorkerReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkerService {

    private final WorkerReadMapper workerReadMapper;
    private final WorkerRepository workerRepository;

    public Optional<WorkerReadDto> findById(Integer id) {
        return workerRepository.findById(id).map(workerReadMapper::map);
    }

    public Optional<WorkerReadDto> findByEmail(String email) {
        return workerRepository.findByEmail(email).map(workerReadMapper::map);
    }

    public List<WorkerReadDto> findAll() {

        return workerRepository.findAll().stream()
                .map(workerReadMapper::map).toList();
    }
}
