package by.kozlov.hibernate.starter.service;

import by.kozlov.hibernate.starter.dao.WorkerDao;
import by.kozlov.hibernate.starter.dto.ProductionDto;
import by.kozlov.hibernate.starter.dto.WorkerDto;
import by.kozlov.hibernate.starter.entity.Worker;
import by.kozlov.hibernate.starter.mapper.WorkerMapper;
import by.kozlov.hibernate.starter.utils.HibernateUtil;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class WorkerService {

    private final SessionFactory sessionFactory = HibernateUtil.getConfig().buildSessionFactory();
    private static final WorkerService INSTANCE = new WorkerService();
    private final WorkerDao workerDao = WorkerDao.getInstance();
    private final WorkerMapper workerMapper = WorkerMapper.getInstance();

    public Optional<WorkerDto> findById(Integer id) {

        try (var session = sessionFactory.openSession()) {
            Optional<WorkerDto> production;
            session.beginTransaction();
            production = workerDao.findById(session,id)
                    .map(workerMapper::mapFrom);
            session.getTransaction().commit();
            return production;
        }
    }

    public Optional<WorkerDto> findByEmail(String email) {

        try (var session = sessionFactory.openSession()) {
            Optional<WorkerDto> worker;
            session.beginTransaction();
            worker = workerDao.findByEmail(session,email)
                    .map(workerMapper::mapFrom);
            session.getTransaction().commit();
            return worker;
        }
    }

    public List<WorkerDto> findAll() {
        try (var session = sessionFactory.openSession()) {

            List<WorkerDto> workers;
            session.beginTransaction();
            workers =  workerDao.findAll(session).stream()
                    .map(workerMapper::mapFrom).collect(Collectors.toList());
            session.getTransaction().commit();
            return workers;
        }
    }

    public Optional<WorkerDto> find(String id) {
        try (var session = sessionFactory.openSession()) {
            Optional<WorkerDto> production;
            session.beginTransaction();
            production = workerDao.findById(session,Integer.parseInt(id))
                    .map(workerMapper::mapFrom);
            session.getTransaction().commit();
            return production;
        }
    }


    private WorkerService() {
    }

    public static WorkerService getInstance() {
        return INSTANCE;
    }


}
