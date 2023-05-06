package by.kozlov.hibernate.starter.service;

import by.kozlov.hibernate.starter.dto.WorkerDto;
import by.kozlov.hibernate.starter.exception.ValidationException;
import by.kozlov.hibernate.starter.utils.HibernateUtil;
import by.kozlov.hibernate.starter.validator.CreateProductionValidator;
import by.kozlov.hibernate.starter.validator.UpdateProductionValidator;
import by.kozlov.hibernate.starter.dao.ProductionDao;
import by.kozlov.hibernate.starter.dto.CreateProductionDto;
import by.kozlov.hibernate.starter.dto.ProductionDto;
import by.kozlov.hibernate.starter.dto.UpdateProductionDto;
import by.kozlov.hibernate.starter.mapper.CreateProductionMapper;
import by.kozlov.hibernate.starter.mapper.ProductionMapper;
import by.kozlov.hibernate.starter.mapper.UpdateProductionMapper;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductionService {

    private final SessionFactory sessionFactory = HibernateUtil.getConfig().buildSessionFactory();

    private static final ProductionService INSTANCE = new ProductionService();

    private final ProductionDao productionDao = ProductionDao.getInstance();
    private final ProductionMapper productionMapper = ProductionMapper.getInstance();
    private final CreateProductionValidator createProductionValidator = CreateProductionValidator.getInstance();
    private final CreateProductionMapper createProductionMapper = CreateProductionMapper.getInstance();
    private final UpdateProductionMapper updateProductionMapper = UpdateProductionMapper.getInstance();
    private final UpdateProductionValidator updateProductionValidator = UpdateProductionValidator.getInstance();

    public List<ProductionDto> findAllByWorkerId(Integer id) {
        try (var session = sessionFactory.openSession()) {
            List<ProductionDto> productions;
            session.beginTransaction();
            productions = productionDao.findAllByWorkerId(session,id).stream()
                    .map(productionMapper::mapFrom).collect(Collectors.toList());
            session.getTransaction().commit();
            return productions;
        }
    }

    public List<ProductionDto> findAll() {
        try (var session = sessionFactory.openSession()) {
            List<ProductionDto> productions;
            session.beginTransaction();
            productions = productionDao.findAll(session).stream()
                    .map(productionMapper::mapFrom).collect(Collectors.toList());
            session.getTransaction().commit();
            return productions;
        }
    }

    public Optional<ProductionDto> findById(Integer id) {
        try (var session = sessionFactory.openSession()) {
            Optional<ProductionDto> production;
            session.beginTransaction();
            production = productionDao.findById(session,id)
                    .map(productionMapper::mapFrom);
            session.getTransaction().commit();
            return production;
        }
    }

    public Integer create(CreateProductionDto productionDto) {

        try (var session = sessionFactory.openSession()) {
            var validationResult = createProductionValidator.isValid(productionDto);
            if (!validationResult.isValid()) {
                throw new ValidationException(validationResult.getErrors());
            }
            var productionEntity = createProductionMapper.mapFrom(productionDto);
            session.beginTransaction();
            productionEntity = productionDao.save(session,productionEntity);
            session.getTransaction().commit();
            return productionEntity.getId();
        }
    }

    public boolean delete(Integer id) {
        try (var session = sessionFactory.openSession()) {
            boolean result;
            session.beginTransaction();
            result = productionDao.delete(session,id);
            session.getTransaction().commit();
            return result;
        }
    }

    public boolean update(UpdateProductionDto productionDto) {

        try (var session = sessionFactory.openSession()) {
            boolean result;
            var validationResult = updateProductionValidator.isValid(productionDto);
            if (!validationResult.isValid()) {
                throw new ValidationException(validationResult.getErrors());
            }
            var productionEntity = updateProductionMapper.mapFrom(productionDto);
            session.beginTransaction();
            result = productionDao.update(session, productionEntity);
            session.getTransaction().commit();
            return result;
        }
    }

    private ProductionService() {}

    public static ProductionService getInstance() {
        return INSTANCE;
    }
}
