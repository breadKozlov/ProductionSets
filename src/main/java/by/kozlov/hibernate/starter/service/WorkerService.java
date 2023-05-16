package by.kozlov.hibernate.starter.service;

import by.kozlov.hibernate.starter.dao.WorkerRepository;
import by.kozlov.hibernate.starter.dto.WorkerDto;
import by.kozlov.hibernate.starter.mapper.BrigadeMapper;
import by.kozlov.hibernate.starter.mapper.WorkerMapper;
import by.kozlov.hibernate.starter.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class WorkerService {

    private final Session session;
    private static final WorkerService INSTANCE = new WorkerService();
    private final WorkerMapper workerMapper;
    private final WorkerRepository workerRepository;
    private final SessionFactory sessionFactory;

    private WorkerService() {
        sessionFactory = HibernateUtil.getConfig().buildSessionFactory();
        session = (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(),
                    new Class[]{Session.class},
                    (proxy, method, args1) -> method.invoke(sessionFactory.getCurrentSession(), args1));
        workerRepository = new WorkerRepository(session);
        BrigadeMapper brigadeMapper = new BrigadeMapper();
        workerMapper = new WorkerMapper(brigadeMapper);
    }

    public Optional<WorkerDto> findById(Integer id) {

        try(session) {
            Optional<WorkerDto> worker;
            session.beginTransaction();
            worker = workerRepository.findById(id).map(workerMapper::mapFrom);
            session.getTransaction().commit();
            return worker;
        }
    }

    /*public Optional<WorkerDto> findById(Integer id) {

        try (var session = sessionFactory.openSession()) {
            Optional<WorkerDto> production;
            session.beginTransaction();
            production = workerDao.findById(session,id)
                    .map(workerMapper::mapFrom);
            session.getTransaction().commit();
            return production;
        }
    }*/

    public Optional<WorkerDto> findByEmail(String email) {

        try(session) {
            Optional<WorkerDto> worker;
            session.beginTransaction();
            worker = workerRepository.findByEmail(email).map(workerMapper::mapFrom);
            session.getTransaction().commit();
            return worker;
        }
    }

    public List<WorkerDto> findAll() {

        try(session) {
            List<WorkerDto> workers;
            session.beginTransaction();
            workers =  workerRepository.findAll().stream()
                    .map(workerMapper::mapFrom).collect(Collectors.toList());
            session.getTransaction().commit();
            return workers;
        }
    }

    public Optional<WorkerDto> find(String id) {

        try(session) {
                Optional<WorkerDto> worker;
                session.beginTransaction();
                worker = workerRepository.findById(Integer.parseInt(id))
                    .map(workerMapper::mapFrom);
                session.getTransaction().commit();
                return worker;
            }
    }


    public static WorkerService getInstance() {
        return INSTANCE;
    }
}
