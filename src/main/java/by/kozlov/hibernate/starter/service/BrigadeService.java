package by.kozlov.hibernate.starter.service;

import by.kozlov.hibernate.starter.dao.BrigadeRepository;
import by.kozlov.hibernate.starter.dto.BrigadeDto;
import by.kozlov.hibernate.starter.mapper.BrigadeMapper;
import by.kozlov.hibernate.starter.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BrigadeService {

    private final SessionFactory sessionFactory;
    private static final BrigadeService INSTANCE = new BrigadeService();
    private final BrigadeRepository brigadeRepository;
    private final Session session;
    private final BrigadeMapper brigadeMapper = new BrigadeMapper();

    private BrigadeService() {
        sessionFactory = HibernateUtil.getConfig().buildSessionFactory();
        session = (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(),
                new Class[]{Session.class},
                (proxy, method, args1) -> method.invoke(sessionFactory.getCurrentSession(), args1));
        brigadeRepository = new BrigadeRepository(session);
    }

    public Optional<BrigadeDto> find(String id) {
        try (session) {
            Optional<BrigadeDto> brigades;
            session.beginTransaction();
            brigades = brigadeRepository.findById(Integer.parseInt(id))
                    .map(brigadeMapper::mapFrom);
            session.getTransaction().commit();
            return brigades;
        }
    }
    public List<BrigadeDto> findAll() {
        try (session) {
            List<BrigadeDto> brigades;
            session.beginTransaction();
            brigades = brigadeRepository.findAll().stream()
                    .map(brigadeMapper::mapFrom).collect(Collectors.toList());
            session.getTransaction().commit();
            return brigades;
        }
    }

    public Optional<BrigadeDto> findById(Integer id) {
        try (session) {
            Optional<BrigadeDto> brigades;
            session.beginTransaction();
            brigades = brigadeRepository.findById(id)
                    .map(brigadeMapper::mapFrom);
            session.getTransaction().commit();
            return brigades;
        }
    }

    public static BrigadeService getInstance() {
        return INSTANCE;
    }

}
