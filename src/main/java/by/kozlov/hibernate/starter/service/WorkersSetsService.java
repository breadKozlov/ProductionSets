package by.kozlov.hibernate.starter.service;

import by.kozlov.hibernate.starter.dao.*;
import by.kozlov.hibernate.starter.dto.*;
import by.kozlov.hibernate.starter.entity.WorkersSets;
import by.kozlov.hibernate.starter.exception.ValidationException;
import by.kozlov.hibernate.starter.mapper.*;
import by.kozlov.hibernate.starter.utils.HibernateUtil;
import by.kozlov.hibernate.starter.validator.CreateWorkersSetsValidator;
import by.kozlov.hibernate.starter.validator.UpdateWorkersSetsValidator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class WorkersSetsService {

    private static final WorkersSetsService INSTANCE = new WorkersSetsService();
    private final WorkersSetsRepository workersSetsRepository;
    private final WorkersSetsMapper workersSetsMapper;

    private final CreateWorkersSetsValidator createWorkersSetsValidator = CreateWorkersSetsValidator.getInstance();
    private final CreateWorkersSetsMapper createWorkersSetsMapper;

    private final UpdateWorkersSetsMapper updateWorkersSetsMapper;
    private final UpdateWorkersSetsValidator updateWorkersSetsValidator = UpdateWorkersSetsValidator.getInstance();

    private final Session session;

    private WorkersSetsService() {

        SessionFactory sessionFactory = HibernateUtil.getConfig().buildSessionFactory();
        session = HibernateUtil.getProxySession(sessionFactory);
        workersSetsRepository = new WorkersSetsRepository(session);
        var setMapper = new SetMapper();
        var brigadeMapper = new BrigadeMapper();
        var workerMapper = new WorkerMapper(brigadeMapper);
        var setRepository = new SetRepository(session);
        var workerRepository = new WorkerRepository(session);
        workersSetsMapper = new WorkersSetsMapper(setMapper,workerMapper);
        createWorkersSetsMapper = new CreateWorkersSetsMapper(setRepository,workerRepository);
        updateWorkersSetsMapper = new UpdateWorkersSetsMapper(setRepository,workerRepository);
    }
    public List<WorkersSetsDto> findAllByWorkerId(Integer id) {

        try (session) {
            List<WorkersSetsDto> workersSets;
            session.beginTransaction();
            workersSets = workersSetsRepository.findAllByWorkerId(id).stream()
                    .map(workersSetsMapper::mapFrom).collect(Collectors.toList());
            session.getTransaction().commit();
            return workersSets;
        }
    }

    public List<Object[]> findAllProdSetsById(Integer id) {
        try(session) {
            List<Object[]> sum;
            session.beginTransaction();
            sum = workersSetsRepository.findAllProdSetsById(id);
            session.getTransaction().commit();
            return sum;
        }
    }

    public List<WorkersSetsDto> findAll() {
        try (session) {
            List<WorkersSetsDto> workersSets;
            session.beginTransaction();
            workersSets = workersSetsRepository.findAll().stream()
                    .map(workersSetsMapper::mapFrom).collect(Collectors.toList());
            session.getTransaction().commit();
            return workersSets;
        }
    }

    public Integer create(CreateWorkersSetsDto workersSetsDto) {
        try (session;
             var validationFactory = Validation.buildDefaultValidatorFactory()) {

            var validator = validationFactory.getValidator();
            var validationResult = validator.validate(workersSetsDto);
            if (!validationResult.isEmpty()) {
                throw new ConstraintViolationException(validationResult);
            }
            session.beginTransaction();
            var productionEntity = createWorkersSetsMapper.mapFrom(workersSetsDto);

            productionEntity = workersSetsRepository.save(productionEntity);
            session.getTransaction().commit();
            return productionEntity.getId();
        }
    }

    public boolean delete(Integer id) {
        try (session) {
            Optional<WorkersSets> maybe;
            session.beginTransaction();
            maybe = workersSetsRepository.findById(id);
            maybe.ifPresent(it -> workersSetsRepository.delete(id));
            session.getTransaction().commit();
            return maybe.isPresent();
        }
    }

    public Optional<WorkersSetsDto> findById(Integer id) {

        try (session) {
            Optional<WorkersSetsDto> workersSetsDto;
            session.beginTransaction();
            workersSetsDto = workersSetsRepository.findById(id)
                    .map(workersSetsMapper::mapFrom);
            session.getTransaction().commit();
            return workersSetsDto;
        }
    }

    public void update(UpdateWorkersSetsDto workersSetsDto) {
        try (session;
             var validationFactory = Validation.buildDefaultValidatorFactory()) {

            var validator = validationFactory.getValidator();
            var validationResult = validator.validate(workersSetsDto);
            if (!validationResult.isEmpty()) {
                throw new ConstraintViolationException(validationResult);
            }
            session.beginTransaction();
            var productionEntity = updateWorkersSetsMapper.mapFrom(workersSetsDto);

            workersSetsRepository.update(productionEntity);
            session.getTransaction().commit();
        }
    }

    public static WorkersSetsService getInstance() {
        return INSTANCE;
    }
}
