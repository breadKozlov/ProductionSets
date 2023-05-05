package by.kozlov.hibernate.starter.service;

import by.kozlov.hibernate.starter.dao.WorkerDao;
import by.kozlov.hibernate.starter.dto.WorkerDto;
import by.kozlov.hibernate.starter.mapper.WorkerMapper;
import by.kozlov.hibernate.starter.utils.HibernateUtil;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class WorkerService {

    private final Configuration configuration = HibernateUtil.getConfig();

    private static final WorkerService INSTANCE = new WorkerService();
    private final WorkerDao workerDao = WorkerDao.getInstance();
    private final WorkerMapper workerMapper = WorkerMapper.getInstance();

    public Optional<WorkerDto> findId(Integer id) {

        return workerDao.findById(id).map(
                workerMapper::mapFrom
        );
    }

    public Optional<WorkerDto> findByEmail(String email) {

        return workerDao.findByEmail(email).map(
                workerMapper::mapFrom
        );
    }

    public List<WorkerDto> findAll() {

        List<WorkerDto> workers;
        try (var sessionFactory = configuration.buildSessionFactory();
             var session = sessionFactory.openSession()) {

            session.beginTransaction();
            workers =  workerDao.findAllHibernate(session).stream()
                    .map(workerMapper::mapFrom).collect(Collectors.toList());
            session.getTransaction().commit();
        }
        return workers;
    }

    public Optional<WorkerDto> find(String id) {
        return workerDao.findById(Integer.parseInt(id)).map(
                workerMapper::mapFrom
        );
    }


    private WorkerService() {
    }

    public static WorkerService getInstance() {
        return INSTANCE;
    }


}
