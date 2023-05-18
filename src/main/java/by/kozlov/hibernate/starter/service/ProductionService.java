package by.kozlov.hibernate.starter.service;

import by.kozlov.hibernate.starter.dao.*;
import by.kozlov.hibernate.starter.dto.WorkerDto;
import by.kozlov.hibernate.starter.entity.Production;
import by.kozlov.hibernate.starter.exception.ValidationException;
import by.kozlov.hibernate.starter.mapper.*;
import by.kozlov.hibernate.starter.utils.HibernateUtil;
import by.kozlov.hibernate.starter.validator.CreateProductionValidator;
import by.kozlov.hibernate.starter.validator.UpdateProductionValidator;
import by.kozlov.hibernate.starter.dto.CreateProductionDto;
import by.kozlov.hibernate.starter.dto.ProductionDto;
import by.kozlov.hibernate.starter.dto.UpdateProductionDto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductionService {

    private static final ProductionService INSTANCE = new ProductionService();

    private final ProductionRepository productionRepository;
    private final ProductionMapper productionMapper;
    private final CreateProductionValidator createProductionValidator = CreateProductionValidator.getInstance();
    private final CreateProductionMapper createProductionMapper;
    private final UpdateProductionMapper updateProductionMapper;
    private final UpdateProductionValidator updateProductionValidator = UpdateProductionValidator.getInstance();

    private final Session session;


    private ProductionService() {

        SessionFactory sessionFactory = HibernateUtil.getConfig().buildSessionFactory();
        session = HibernateUtil.getProxySession(sessionFactory);
        productionRepository = new ProductionRepository(session);
        var brigadeMapper = new BrigadeMapper();
        var workerMapper = new WorkerMapper(brigadeMapper);
        var setMapper = new SetMapper();
        var setRepository = new SetRepository(session);
        var workerRepository = new WorkerRepository(session);
        productionMapper = new ProductionMapper(workerMapper,setMapper);
        createProductionMapper = new CreateProductionMapper(setRepository,workerRepository);
        updateProductionMapper = new UpdateProductionMapper(setRepository,workerRepository);
    }
    public List<ProductionDto> findAllByWorkerId(Integer id) {
        try (session) {
            List<ProductionDto> productions;
            session.beginTransaction();
            productions = productionRepository.findAllByWorkerId(id).stream()
                    .map(productionMapper::mapFrom).collect(Collectors.toList());
            session.getTransaction().commit();
            return productions;
        }
    }

    public List<ProductionDto> findAll() {
        try (session) {
            List<ProductionDto> productions;
            session.beginTransaction();
            productions = productionRepository.findAll().stream()
                    .map(productionMapper::mapFrom).collect(Collectors.toList());
            session.getTransaction().commit();
            return productions;
        }
    }

    public Optional<ProductionDto> findById(Integer id) {
        try (session) {
            Optional<ProductionDto> production;
            session.beginTransaction();
            production = productionRepository.findById(id)
                    .map(productionMapper::mapFrom);
            session.getTransaction().commit();
            return production;
        }
    }

    public Integer create(CreateProductionDto productionDto) {

        try (session;
             var validationFactory = Validation.buildDefaultValidatorFactory()) {

            var validator = validationFactory.getValidator();
            var validationResult = validator.validate(productionDto);
            if (!validationResult.isEmpty()) {
                throw new ConstraintViolationException(validationResult);
            }
            session.beginTransaction();
            var productionEntity = createProductionMapper.mapFrom(productionDto);
            productionEntity = productionRepository.save(productionEntity);
            session.getTransaction().commit();
            return productionEntity.getId();
        }
    }

    public boolean delete(Integer id) {
        try (session) {
            Optional<Production> maybe;
            session.beginTransaction();
            maybe = productionRepository.findById(id);
            maybe.ifPresent(it -> productionRepository.delete(id));
            session.getTransaction().commit();
            return maybe.isPresent();
        }
    }

    public void update(UpdateProductionDto productionDto) {

        try (session;
             var validationFactory = Validation.buildDefaultValidatorFactory()) {

            var validator = validationFactory.getValidator();
            var validationResult = validator.validate(productionDto);
            if (!validationResult.isEmpty()) {
                throw new ConstraintViolationException(validationResult);
            }
            session.beginTransaction();
            var productionEntity = updateProductionMapper.mapFrom(productionDto);
            productionRepository.update(productionEntity);
            session.getTransaction().commit();
        }
    }

    public List<Object[]> findSumReqMaterials() {
        try(session) {
            List<Object[]> sum;
            session.beginTransaction();
            sum = productionRepository.findSumAllProdSets();
            session.getTransaction().commit();
            return sum;
        }
    }

    public static ProductionService getInstance() {
        return INSTANCE;
    }
}
