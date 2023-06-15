package by.kozlov.spring.service;

import by.kozlov.spring.dto.CreateWorkersSetsDto;
import by.kozlov.spring.dto.UpdateWorkersSetsDto;
import by.kozlov.spring.dto.WorkersSetsDto;
import by.kozlov.spring.database.entity.WorkersSets;
import by.kozlov.spring.mapper.CreateWorkersSetsMapper;
import by.kozlov.spring.mapper.UpdateWorkersSetsMapper;
import by.kozlov.spring.mapper.WorkersSetsMapper;
import by.kozlov.spring.database.repository.WorkersSetsRepository;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorkersSetsService {
    private final WorkersSetsRepository workersSetsRepository;
    private final WorkersSetsMapper workersSetsMapper;
    private final CreateWorkersSetsMapper createWorkersSetsMapper;
    private final UpdateWorkersSetsMapper updateWorkersSetsMapper;

    @Autowired
    public WorkersSetsService(WorkersSetsRepository workersSetsRepository,
                              WorkersSetsMapper workersSetsMapper,
                              CreateWorkersSetsMapper createWorkersSetsMapper,
                              UpdateWorkersSetsMapper updateWorkersSetsMapper) {
        this.workersSetsRepository = workersSetsRepository;
        this.workersSetsMapper = workersSetsMapper;
        this.createWorkersSetsMapper = createWorkersSetsMapper;
        this.updateWorkersSetsMapper = updateWorkersSetsMapper;
    }

    public List<WorkersSetsDto> findAllByWorkerId(Integer id) {

        return workersSetsRepository.findAllByWorkerId(id).stream()
                    .map(workersSetsMapper::mapFrom).collect(Collectors.toList());
    }

    public List<Object[]> findAllProdSetsById(Integer id) {

        List<Object[]> sum;
        sum = workersSetsRepository.findAllProdSetsById(id);
        return sum;
    }

    public List<WorkersSetsDto> findAll() {
        return workersSetsRepository.findAll().stream()
                .map(workersSetsMapper::mapFrom).collect(Collectors.toList());
    }

    public Integer create(CreateWorkersSetsDto workersSetsDto) {
        try (var validationFactory = Validation.buildDefaultValidatorFactory()) {

            var validator = validationFactory.getValidator();
            var validationResult = validator.validate(workersSetsDto);
            if (!validationResult.isEmpty()) {
                throw new ConstraintViolationException(validationResult);
            }
            var productionEntity = createWorkersSetsMapper.mapFrom(workersSetsDto);

            productionEntity = workersSetsRepository.save(productionEntity);
            return productionEntity.getId();
        }
    }

    public boolean delete(Integer id) {

        Optional<WorkersSets> maybe;
        maybe = workersSetsRepository.findById(id);
        maybe.ifPresent(it -> workersSetsRepository.delete(maybe.orElseThrow()));
        return maybe.isPresent();
    }

    public Optional<WorkersSetsDto> findById(Integer id) {
        return workersSetsRepository.findById(id)
                .map(workersSetsMapper::mapFrom);
    }

    public void update(UpdateWorkersSetsDto workersSetsDto) {
        try (var validationFactory = Validation.buildDefaultValidatorFactory()) {

            var validator = validationFactory.getValidator();
            var validationResult = validator.validate(workersSetsDto);
            if (!validationResult.isEmpty()) {
                throw new ConstraintViolationException(validationResult);
            }
            var productionEntity = updateWorkersSetsMapper.mapFrom(workersSetsDto);
            workersSetsRepository.saveAndFlush(productionEntity);
        }
    }
}

