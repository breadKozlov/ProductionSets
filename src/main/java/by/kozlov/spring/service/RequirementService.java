package by.kozlov.spring.service;

import by.kozlov.spring.dto.RequirementDto;
import by.kozlov.spring.entity.Requirement;
import by.kozlov.spring.mapper.CreateRequirementMapper;
import by.kozlov.spring.mapper.RequirementMapper;
import by.kozlov.spring.mapper.UpdateRequirementMapper;
import by.kozlov.spring.repository.RequirementRepository;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import by.kozlov.spring.dto.CreateRequirementDto;
import by.kozlov.spring.dto.UpdateRequirementDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RequirementService {

    private final RequirementRepository requirementRepository;
    private final CreateRequirementMapper createRequirementMapper;
    private final UpdateRequirementMapper updateRequirementMapper;
    private final RequirementMapper requirementMapper;

    @Autowired
    public RequirementService(RequirementRepository requirementRepository,
                              CreateRequirementMapper createRequirementMapper,
                              UpdateRequirementMapper updateRequirementMapper,
                              RequirementMapper requirementMapper) {
        this.requirementRepository = requirementRepository;
        this.createRequirementMapper = createRequirementMapper;
        this.updateRequirementMapper = updateRequirementMapper;
        this.requirementMapper = requirementMapper;
    }

    public List<RequirementDto> findAllBySetId(Integer id) {

        return requirementRepository.findAllBySetId(id).stream()
                .map(requirementMapper::mapFrom).collect(Collectors.toList());
    }

    public List<RequirementDto> findAll() {
        return requirementRepository.findAll().stream()
                .map(requirementMapper::mapFrom).collect(Collectors.toList());
    }

    public List<Object[]> findSumReqMaterials() {
        List<Object[]> sum;
        sum = requirementRepository.findSumAllReqMat();
        return sum;
    }

    public Optional<RequirementDto> findById(Integer id) {
        return requirementRepository.findById(id)
                .map(requirementMapper::mapFrom);
    }

    public Integer create(CreateRequirementDto requirementDto) {
        try (var validationFactory = Validation.buildDefaultValidatorFactory()) {

            var validator = validationFactory.getValidator();
            var validationResult = validator.validate(requirementDto);
            if (!validationResult.isEmpty()) {
                throw new ConstraintViolationException(validationResult);
            }
            var productionEntity = createRequirementMapper.mapFrom(requirementDto);
            productionEntity = requirementRepository.saveAndFlush(productionEntity);
            return productionEntity.getId();
        }
    }

    public boolean delete(Integer id) {
        Optional<Requirement> maybe;
        maybe = requirementRepository.findById(id);
        maybe.ifPresent(it -> requirementRepository.delete(maybe.orElseThrow()));
        return maybe.isPresent();
    }

    public void update(UpdateRequirementDto requirementDto) {

        try (var validationFactory = Validation.buildDefaultValidatorFactory()) {

            var validator = validationFactory.getValidator();
            var validationResult = validator.validate(requirementDto);
            if (!validationResult.isEmpty()) {
                throw new ConstraintViolationException(validationResult);
            }
            var productionEntity = updateRequirementMapper.mapFrom(requirementDto);
            requirementRepository.saveAndFlush(productionEntity);
        }
    }
}
