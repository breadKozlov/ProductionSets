package by.kozlov.jdbc.starter.service;

import by.kozlov.jdbc.starter.dao.RequirementDao;
import by.kozlov.jdbc.starter.dto.*;
import by.kozlov.jdbc.starter.exception.ValidationException;
import by.kozlov.jdbc.starter.mapper.CreateRequirementMapper;
import by.kozlov.jdbc.starter.mapper.RequirementMapper;
import by.kozlov.jdbc.starter.mapper.UpdateRequirementMapper;
import by.kozlov.jdbc.starter.validator.CreateRequirementValidator;
import by.kozlov.jdbc.starter.validator.UpdateRequirementValidator;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RequirementService {

    private static final RequirementService INSTANCE = new RequirementService();
    private final RequirementDao requirementDao = RequirementDao.getInstance();

    private final CreateRequirementMapper createRequirementMapper = CreateRequirementMapper.getInstance();
    private final CreateRequirementValidator createRequirementValidator = CreateRequirementValidator.getInstance();

    private final UpdateRequirementValidator updateRequirementValidator = UpdateRequirementValidator.getInstance();
    private final UpdateRequirementMapper updateRequirementMapper = UpdateRequirementMapper.getInstance();
    private final RequirementMapper requirementMapper = RequirementMapper.getInstance();
    public List<RequirementDto> findAllBySetId(Integer id) {
        return requirementDao.findAllBySetId(id).stream().map(
                requirementMapper::mapFrom
        ).collect(Collectors.toList());
    }

    public List<RequirementDto> findAll() {
        return requirementDao.findAll().stream().map(
                requirementMapper::mapFrom
        ).collect(Collectors.toList());
    }

    public Optional<RequirementDto> findById(Integer id) {
        return requirementDao.findById(id).map(requirementMapper::mapFrom);
    }

    public Integer create(CreateRequirementDto requirementDto) {
        var validationResult = createRequirementValidator.isValid(requirementDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
        var requirementEntity = createRequirementMapper.mapFrom(requirementDto);
        requirementDao.save(requirementEntity);
        return requirementEntity.getId();
    }

    public boolean delete(Integer id) {
        return requirementDao.delete(id);
    }

    public boolean update(UpdateRequirementDto requirementDto) {

        var validationResult = updateRequirementValidator.isValid(requirementDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
        var productionEntity = updateRequirementMapper.mapFrom(requirementDto);
        return requirementDao.update(productionEntity);
    }

    private RequirementService() {}
    public static RequirementService getInstance() {
        return INSTANCE;
    }

}
