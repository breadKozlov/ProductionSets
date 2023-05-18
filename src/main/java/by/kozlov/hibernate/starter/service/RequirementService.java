package by.kozlov.hibernate.starter.service;

import by.kozlov.hibernate.starter.dao.*;
import by.kozlov.hibernate.starter.dto.CreateRequirementDto;
import by.kozlov.hibernate.starter.dto.ProductionDto;
import by.kozlov.hibernate.starter.dto.RequirementDto;
import by.kozlov.hibernate.starter.dto.UpdateRequirementDto;
import by.kozlov.hibernate.starter.entity.Requirement;
import by.kozlov.hibernate.starter.exception.ValidationException;
import by.kozlov.hibernate.starter.mapper.*;
import by.kozlov.hibernate.starter.utils.HibernateUtil;
import by.kozlov.hibernate.starter.validator.CreateRequirementValidator;
import by.kozlov.hibernate.starter.validator.UpdateRequirementValidator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RequirementService {

    private static final RequirementService INSTANCE = new RequirementService();
    private final RequirementRepository requirementRepository;

    private final CreateRequirementMapper createRequirementMapper;
    private final CreateRequirementValidator createRequirementValidator = CreateRequirementValidator.getInstance();

    private final UpdateRequirementValidator updateRequirementValidator = UpdateRequirementValidator.getInstance();
    private final UpdateRequirementMapper updateRequirementMapper;
    private final RequirementMapper requirementMapper;

    private final Session session;


    private RequirementService() {

        SessionFactory sessionFactory = HibernateUtil.getConfig().buildSessionFactory();
        session = HibernateUtil.getProxySession(sessionFactory);
        requirementRepository = new RequirementRepository(session);
        var setMapper = new SetMapper();
        var materialMapper = new MaterialMapper();
        var materialRepository = new MaterialRepository(session);
        var setRepository = new SetRepository(session);
        requirementMapper = new RequirementMapper(setMapper,materialMapper);
        createRequirementMapper = new CreateRequirementMapper(setRepository,materialRepository);
        updateRequirementMapper = new UpdateRequirementMapper(setRepository,materialRepository);
    }
    public List<RequirementDto> findAllBySetId(Integer id) {

        try (session) {
            List<RequirementDto> req;
            session.beginTransaction();
            req = requirementRepository.findAllBySetId(id).stream()
                    .map(requirementMapper::mapFrom).collect(Collectors.toList());
            session.getTransaction().commit();
            return req;
        }
    }

    public List<RequirementDto> findAll() {
        try (session) {
            List<RequirementDto> req;
            session.beginTransaction();
            req = requirementRepository.findAll().stream()
                    .map(requirementMapper::mapFrom).collect(Collectors.toList());
            session.getTransaction().commit();
            return req;
        }
    }

    public List<Object[]> findSumReqMaterials() {
        try(session) {
            List<Object[]> sum;
            session.beginTransaction();
            sum = requirementRepository.findSumAllReqMat();
            session.getTransaction().commit();
            return sum;
        }
    }

    public Optional<RequirementDto> findById(Integer id) {
        try (session) {
            Optional<RequirementDto> req;
            session.beginTransaction();
            req = requirementRepository.findById(id)
                    .map(requirementMapper::mapFrom);
            session.getTransaction().commit();
            return req;
        }
    }

    public Integer create(CreateRequirementDto requirementDto) {
        try (session;
             var validationFactory = Validation.buildDefaultValidatorFactory()) {

            var validator = validationFactory.getValidator();
            var validationResult = validator.validate(requirementDto);
            if (!validationResult.isEmpty()) {
                throw new ConstraintViolationException(validationResult);
            }
            session.beginTransaction();
            var productionEntity = createRequirementMapper.mapFrom(requirementDto);
            productionEntity = requirementRepository.save(productionEntity);
            session.getTransaction().commit();
            return productionEntity.getId();
        }
    }

    public boolean delete(Integer id) {
        try (session) {
            Optional< Requirement> maybe;
            session.beginTransaction();
            maybe = requirementRepository.findById(id);
            maybe.ifPresent(it -> requirementRepository.delete(id));
            session.getTransaction().commit();
            return maybe.isPresent();
        }
    }

    public void update(UpdateRequirementDto requirementDto) {

        try (session;
             var validationFactory = Validation.buildDefaultValidatorFactory()) {

            var validator = validationFactory.getValidator();
            var validationResult = validator.validate(requirementDto);
            if (!validationResult.isEmpty()) {
                throw new ConstraintViolationException(validationResult);
            }
            session.beginTransaction();
            var productionEntity = updateRequirementMapper.mapFrom(requirementDto);
            requirementRepository.update(productionEntity);
            session.getTransaction().commit();
        }
    }

    public static RequirementService getInstance() {
        return INSTANCE;
    }

}
