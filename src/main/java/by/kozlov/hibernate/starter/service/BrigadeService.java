package by.kozlov.hibernate.starter.service;

import by.kozlov.hibernate.starter.dao.BrigadeDao;
import by.kozlov.hibernate.starter.dto.BrigadeDto;
import by.kozlov.hibernate.starter.dto.MaterialDto;
import by.kozlov.hibernate.starter.mapper.BrigadeMapper;
import by.kozlov.hibernate.starter.utils.HibernateUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BrigadeService {

    private final SessionFactory sessionFactory = HibernateUtil.getConfig().buildSessionFactory();
    private static final BrigadeService INSTANCE = new BrigadeService();
    private final BrigadeDao brigadeDao = BrigadeDao.getInstance();
    private final BrigadeMapper brigadeMapper = BrigadeMapper.getInstance();

    public Optional<BrigadeDto> find(String id) {
        try (var session = sessionFactory.openSession()) {
            Optional<BrigadeDto> materials;
            session.beginTransaction();
            materials = brigadeDao.findById(session,Integer.parseInt(id))
                    .map(brigadeMapper::mapFrom);
            session.getTransaction().commit();
            return materials;
        }
    }
    public List<BrigadeDto> findAll() {
        try (var session = sessionFactory.openSession()) {
            List<BrigadeDto> materials;
            session.beginTransaction();
            materials = brigadeDao.findAll(session).stream()
                    .map(brigadeMapper::mapFrom).collect(Collectors.toList());
            session.getTransaction().commit();
            return materials;
        }
    }

    public Optional<BrigadeDto> findById(Integer id) {
        try (var session = sessionFactory.openSession()) {
            Optional<BrigadeDto> materials;
            session.beginTransaction();
            materials = brigadeDao.findById(session,id)
                    .map(brigadeMapper::mapFrom);
            session.getTransaction().commit();
            return materials;
        }
    }

    public static BrigadeService getInstance() {
        return INSTANCE;
    }

}
