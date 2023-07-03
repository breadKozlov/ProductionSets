package by.kozlov.spring.service;

import by.kozlov.spring.database.repository.WorkerRepository;
import by.kozlov.spring.dto.ProductionCreateEditDto;
import by.kozlov.spring.dto.ProductionReadDto;
import by.kozlov.spring.dto.WorkerCreateEditDto;
import by.kozlov.spring.dto.WorkerReadDto;
import by.kozlov.spring.mapper.WorkerCreateEditMapper;
import by.kozlov.spring.mapper.WorkerReadMapper;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WorkerService {

    private final WorkerReadMapper workerReadMapper;
    private final WorkerCreateEditMapper workerCreateEditMapper;
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

    @Transactional
    public WorkerReadDto create(WorkerCreateEditDto workerDto) {

        try (var validationFactory = Validation.buildDefaultValidatorFactory()) {

            var validator = validationFactory.getValidator();
            var validationResult = validator.validate(workerDto);
            if (!validationResult.isEmpty()) {
                throw new ConstraintViolationException(validationResult);
            }
            return Optional.of(workerDto)
                    .map(workerCreateEditMapper::map)
                    .map(workerRepository::saveAndFlush)
                    .map(workerReadMapper::map)
                    .orElseThrow();
        }
    }
}
