package by.kozlov.hibernate.starter.service;

import by.kozlov.hibernate.starter.dao.MaterialDao;
import by.kozlov.hibernate.starter.dto.MaterialDto;
import by.kozlov.hibernate.starter.dto.SetDto;
import by.kozlov.hibernate.starter.mapper.MaterialMapper;
import by.kozlov.hibernate.starter.utils.HibernateUtil;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MaterialService {

    private final SessionFactory sessionFactory = HibernateUtil.getConfig().buildSessionFactory();

    private static final MaterialService INSTANCE = new MaterialService();
    private final MaterialDao materialDao = MaterialDao.getInstance();
    private final MaterialMapper materialMapper = MaterialMapper.getInstance();

    public List<MaterialDto> findAll() {
        try (var session = sessionFactory.openSession()) {
            List<MaterialDto> materials;
            session.beginTransaction();
            materials = materialDao.findAll(session).stream()
                    .map(materialMapper::mapFrom).collect(Collectors.toList());
            session.getTransaction().commit();
            return materials;
        }
    }

    public Optional<MaterialDto> findById(Integer id) {
        try (var session = sessionFactory.openSession()) {
            Optional<MaterialDto> materials;
            session.beginTransaction();
            materials = materialDao.findById(session,id)
                    .map(materialMapper::mapFrom);
            session.getTransaction().commit();
            return materials;
        }
    }

    public Optional<MaterialDto> find(String id) {
        try (var session = sessionFactory.openSession()) {
            Optional<MaterialDto> materials;
            session.beginTransaction();
            materials = materialDao.findById(session,Integer.parseInt(id))
                    .map(materialMapper::mapFrom);
            session.getTransaction().commit();
            return materials;
        }
    }

    public static MaterialService getInstance() {
        return INSTANCE;
    }
}
