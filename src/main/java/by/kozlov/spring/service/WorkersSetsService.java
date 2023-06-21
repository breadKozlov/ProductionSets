package by.kozlov.spring.service;

import by.kozlov.spring.dto.CreateWorkersSetsDto;
import by.kozlov.spring.dto.UpdateWorkersSetsDto;
import by.kozlov.spring.database.entity.WorkersSets;
import by.kozlov.spring.database.repository.WorkersSetsRepository;
import by.kozlov.spring.dto.WorkersSetsCreateEditDto;
import by.kozlov.spring.dto.WorkersSetsReadDto;
import by.kozlov.spring.mapper.WorkersSetsCreateEditMapper;
import by.kozlov.spring.mapper.WorkersSetsReadMapper;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkersSetsService {
    private final WorkersSetsRepository workersSetsRepository;
    private final WorkersSetsReadMapper workersSetsReadMapper;
    private final WorkersSetsCreateEditMapper workersSetsCreateEditMapper;

    public List<WorkersSetsReadDto> findAllByWorkerId(Integer id) {

        return workersSetsRepository.findAllByWorkerId(id).stream()
                    .map(workersSetsReadMapper::map).toList();
    }

    public List<Object[]> findAllProdSetsById(Integer id) {

        List<Object[]> sum;
        sum = workersSetsRepository.findAllProdSetsById(id);
        return sum;
    }

    public List<WorkersSetsReadDto> findAll() {
        return workersSetsRepository.findAll().stream()
                .map(workersSetsReadMapper::map).toList();
    }

    public WorkersSetsReadDto create(WorkersSetsCreateEditDto workersSetsDto) {
        try (var validationFactory = Validation.buildDefaultValidatorFactory()) {

            var validator = validationFactory.getValidator();
            var validationResult = validator.validate(workersSetsDto);
            if (!validationResult.isEmpty()) {
                throw new ConstraintViolationException(validationResult);
            }
            return Optional.of(workersSetsDto)
                    .map(workersSetsCreateEditMapper::map)
                    .map(workersSetsRepository::save)
                    .map(workersSetsReadMapper::map)
                    .orElseThrow();
        }
    }

    public boolean delete(Integer id) {
        return workersSetsRepository.findById(id)
                .map(entity -> {
                    workersSetsRepository.delete(entity);
                    workersSetsRepository.flush();
                    return true;
                }).orElse(false);
    }

    public Optional<WorkersSetsReadDto> findById(Integer id) {
        return workersSetsRepository.findById(id)
                .map(workersSetsReadMapper::map);
    }

    public Optional<WorkersSetsReadDto> update(Integer id, WorkersSetsCreateEditDto workersSetsDto) {
        try (var validationFactory = Validation.buildDefaultValidatorFactory()) {

            var validator = validationFactory.getValidator();
            var validationResult = validator.validate(workersSetsDto);
            if (!validationResult.isEmpty()) {
                throw new ConstraintViolationException(validationResult);
            }
            return workersSetsRepository.findById(id)
                    .map(entity -> workersSetsCreateEditMapper.map(workersSetsDto,entity))
                    .map(workersSetsRepository::saveAndFlush)
                    .map(workersSetsReadMapper::map);
        }
    }
}

