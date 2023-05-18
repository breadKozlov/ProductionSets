package by.kozlov.hibernate.starter.service;

import by.kozlov.hibernate.starter.dao.MaterialRepository;
import by.kozlov.hibernate.starter.dto.MaterialDto;
import by.kozlov.hibernate.starter.mapper.MaterialMapper;
import by.kozlov.hibernate.starter.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MaterialService {

    private final Session session;

    private static final MaterialService INSTANCE = new MaterialService();
    private final MaterialRepository materialRepository;
    private final MaterialMapper materialMapper = new MaterialMapper();

    private MaterialService() {
        SessionFactory sessionFactory = HibernateUtil.getConfig().buildSessionFactory();
        session = HibernateUtil.getProxySession(sessionFactory);
        materialRepository = new MaterialRepository(session);
    }

    public List<MaterialDto> findAll() {
        try (session) {
            List<MaterialDto> materials;
            session.beginTransaction();
            materials = materialRepository.findAll().stream()
                    .map(materialMapper::mapFrom).collect(Collectors.toList());
            session.getTransaction().commit();
            return materials;
        }
    }

    public Optional<MaterialDto> findById(Integer id) {
        try (session) {
            Optional<MaterialDto> materials;
            session.beginTransaction();
            materials = materialRepository.findById(id)
                    .map(materialMapper::mapFrom);
            session.getTransaction().commit();
            return materials;
        }
    }

    public Optional<MaterialDto> find(String id) {
        try (session) {
            Optional<MaterialDto> materials;
            session.beginTransaction();
            materials = materialRepository.findById(Integer.parseInt(id))
                    .map(materialMapper::mapFrom);
            session.getTransaction().commit();
            return materials;
        }
    }

    public static MaterialService getInstance() {
        return INSTANCE;
    }
}
