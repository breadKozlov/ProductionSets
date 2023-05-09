package by.kozlov.hibernate.starter.service;

import by.kozlov.hibernate.starter.dto.CreateRequirementDto;
import by.kozlov.hibernate.starter.dto.ProductionDto;
import by.kozlov.hibernate.starter.dto.RequirementDto;
import by.kozlov.hibernate.starter.dto.UpdateRequirementDto;
import by.kozlov.hibernate.starter.exception.ValidationException;
import by.kozlov.hibernate.starter.mapper.CreateRequirementMapper;
import by.kozlov.hibernate.starter.mapper.RequirementMapper;
import by.kozlov.hibernate.starter.mapper.UpdateRequirementMapper;
import by.kozlov.hibernate.starter.utils.HibernateUtil;
import by.kozlov.hibernate.starter.validator.CreateRequirementValidator;
import by.kozlov.hibernate.starter.validator.UpdateRequirementValidator;
import by.kozlov.hibernate.starter.dao.RequirementDao;
import org.hibernate.SessionFactory;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RequirementService {

    private final SessionFactory sessionFactory = HibernateUtil.getConfig().buildSessionFactory();

    private static final RequirementService INSTANCE = new RequirementService();
    private final RequirementDao requirementDao = RequirementDao.getInstance();

    private final CreateRequirementMapper createRequirementMapper = CreateRequirementMapper.getInstance();
    private final CreateRequirementValidator createRequirementValidator = CreateRequirementValidator.getInstance();

    private final UpdateRequirementValidator updateRequirementValidator = UpdateRequirementValidator.getInstance();
    private final UpdateRequirementMapper updateRequirementMapper = UpdateRequirementMapper.getInstance();
    private final RequirementMapper requirementMapper = RequirementMapper.getInstance();
    public List<RequirementDto> findAllBySetId(Integer id) {

        try (var session = sessionFactory.openSession()) {
            List<RequirementDto> req;
            session.beginTransaction();
            req = requirementDao.findAllBySetId(session,id).stream()
                    .map(requirementMapper::mapFrom).collect(Collectors.toList());
            session.getTransaction().commit();
            return req;
        }
    }

    public List<RequirementDto> findAll() {
        try (var session = sessionFactory.openSession()) {
            List<RequirementDto> req;
            session.beginTransaction();
            req = requirementDao.findAll(session).stream()
                    .map(requirementMapper::mapFrom).collect(Collectors.toList());
            session.getTransaction().commit();
            return req;
        }
    }

    public List<Object[]> findSumReqMaterials() {
        try(var session = sessionFactory.openSession()) {
            List<Object[]> sum;
            session.beginTransaction();
            sum = requirementDao.findSumAllReqMat(session);
            session.getTransaction().commit();
            return sum;
        }
    }

    public Optional<RequirementDto> findById(Integer id) {
        try (var session = sessionFactory.openSession()) {
            Optional<RequirementDto> req;
            session.beginTransaction();
            req = requirementDao.findById(session,id)
                    .map(requirementMapper::mapFrom);
            session.getTransaction().commit();
            return req;
        }
    }

    public Integer create(CreateRequirementDto requirementDto) {
        try (var session = sessionFactory.openSession()) {
            var validationResult = createRequirementValidator.isValid(requirementDto);
            if (!validationResult.isValid()) {
                throw new ValidationException(validationResult.getErrors());
            }
            var productionEntity = createRequirementMapper.mapFrom(requirementDto);
            session.beginTransaction();
            productionEntity = requirementDao.save(session,productionEntity);
            session.getTransaction().commit();
            return productionEntity.getId();
        }
    }

    public boolean delete(Integer id) {
        try (var session = sessionFactory.openSession()) {
            boolean result;
            session.beginTransaction();
            result = requirementDao.delete(session,id);
            session.getTransaction().commit();
            return result;
        }
    }

    public boolean update(UpdateRequirementDto requirementDto) {

        try (var session = sessionFactory.openSession()) {
            boolean result;
            var validationResult = updateRequirementValidator.isValid(requirementDto);
            if (!validationResult.isValid()) {
                throw new ValidationException(validationResult.getErrors());
            }
            var productionEntity = updateRequirementMapper.mapFrom(requirementDto);
            session.beginTransaction();
            result = requirementDao.update(session, productionEntity);
            session.getTransaction().commit();
            return result;
        }
    }

    private RequirementService() {}
    public static RequirementService getInstance() {
        return INSTANCE;
    }

}
