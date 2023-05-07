package by.kozlov.hibernate.starter.service;

import by.kozlov.hibernate.starter.dto.ProductionDto;
import by.kozlov.hibernate.starter.exception.ValidationException;
import by.kozlov.hibernate.starter.dao.WorkersSetsDao;
import by.kozlov.hibernate.starter.dto.CreateWorkersSetsDto;
import by.kozlov.hibernate.starter.dto.WorkersSetsDto;
import by.kozlov.hibernate.starter.mapper.CreateWorkersSetsMapper;
import by.kozlov.hibernate.starter.mapper.WorkersSetsMapper;
import by.kozlov.hibernate.starter.utils.HibernateUtil;
import by.kozlov.hibernate.starter.validator.CreateWorkersSetsValidator;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.stream.Collectors;

public class WorkersSetsService {

    private final SessionFactory sessionFactory = HibernateUtil.getConfig().buildSessionFactory();

    private static final WorkersSetsService INSTANCE = new WorkersSetsService();
    private final WorkersSetsDao workersSetsDao = WorkersSetsDao.getInstance();
    private final WorkersSetsMapper workersSetsMapper = WorkersSetsMapper.getInstance();

    private final CreateWorkersSetsValidator createWorkersSetsValidator = CreateWorkersSetsValidator.getInstance();
    private final CreateWorkersSetsMapper createWorkersSetsMapper = CreateWorkersSetsMapper.getInstance();

    public List<WorkersSetsDto> findAllByWorkerId(Integer id) {

        try (var session = sessionFactory.openSession()) {
            List<WorkersSetsDto> workersSets;
            session.beginTransaction();
            workersSets = workersSetsDao.findAllByWorkerId(session,id).stream()
                    .map(workersSetsMapper::mapFrom).collect(Collectors.toList());
            session.getTransaction().commit();
            return workersSets;
        }
    }

    public List<WorkersSetsDto> findAll() {
        try (var session = sessionFactory.openSession()) {
            List<WorkersSetsDto> workersSets;
            session.beginTransaction();
            workersSets = workersSetsDao.findAll(session).stream()
                    .map(workersSetsMapper::mapFrom).collect(Collectors.toList());
            session.getTransaction().commit();
            return workersSets;
        }
    }

    public Integer create(CreateWorkersSetsDto workersSetsDto) {
        try (var session = sessionFactory.openSession()) {
            var validationResult = createWorkersSetsValidator.isValid(workersSetsDto);
            if (!validationResult.isValid()) {
                throw new ValidationException(validationResult.getErrors());
            }
            var productionEntity = createWorkersSetsMapper.mapFrom(workersSetsDto);
            session.beginTransaction();
            productionEntity = workersSetsDao.save(session,productionEntity);
            session.getTransaction().commit();
            return productionEntity.getId();
        }
    }

    public boolean delete(Integer id) {
        try (var session = sessionFactory.openSession()) {
            boolean result;
            session.beginTransaction();
            result = workersSetsDao.delete(session,id);
            session.getTransaction().commit();
            return result;
        }
    }

    private WorkersSetsService() {
    }

    public static WorkersSetsService getInstance() {
        return INSTANCE;
    }


}
