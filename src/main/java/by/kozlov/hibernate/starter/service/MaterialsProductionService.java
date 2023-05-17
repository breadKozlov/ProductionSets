package by.kozlov.hibernate.starter.service;

import by.kozlov.hibernate.starter.dao.BrigadeRepository;
import by.kozlov.hibernate.starter.dao.MaterialProductionRepository;
import by.kozlov.hibernate.starter.dao.MaterialRepository;
import by.kozlov.hibernate.starter.dto.*;
import by.kozlov.hibernate.starter.entity.MaterialsProduction;
import by.kozlov.hibernate.starter.exception.ValidationException;
import by.kozlov.hibernate.starter.dao.MaterialsProductionDao;
import by.kozlov.hibernate.starter.mapper.*;
import by.kozlov.hibernate.starter.utils.HibernateUtil;
import by.kozlov.hibernate.starter.validator.CreateMaterialsProductionValidator;
import by.kozlov.hibernate.starter.validator.UpdateMaterialsProductionValidator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MaterialsProductionService {

    private final SessionFactory sessionFactory;

    private static final MaterialsProductionService INSTANCE = new MaterialsProductionService();

    private final MaterialProductionRepository materialProductionRepository;

    private final MaterialsProductionMapper materialsProductionMapper;

    private final CreateMaterialsProductionMapper createMaterialsProductionMapper;

    private final UpdateMaterialsProductionValidator updateMaterialsProductionValidator = UpdateMaterialsProductionValidator.getInstance();
    private final UpdateMaterialsProductionMapper updateMaterialsProductionMapper;
    private final CreateMaterialsProductionValidator createMaterialsProductionValidator = CreateMaterialsProductionValidator.getInstance();

    private final Session session;

    private MaterialsProductionService() {
        sessionFactory = HibernateUtil.getConfig().buildSessionFactory();
        session = (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(),
                new Class[]{Session.class},
                (proxy, method, args1) -> method.invoke(sessionFactory.getCurrentSession(), args1));
        materialProductionRepository = new MaterialProductionRepository(session);
        var brigadeMapper = new BrigadeMapper();
        var materialMapper = new MaterialMapper();
        var materialRepository = new MaterialRepository(session);
        var brigadeRepository = new BrigadeRepository(session);
        materialsProductionMapper = new MaterialsProductionMapper(materialMapper,brigadeMapper);
        createMaterialsProductionMapper = new CreateMaterialsProductionMapper(materialRepository,brigadeRepository);
        updateMaterialsProductionMapper = new UpdateMaterialsProductionMapper(materialRepository,brigadeRepository);
    }

    public List<MaterialsProductionDto> findAll() {
        try (session) {
            List<MaterialsProductionDto> productions;
            session.beginTransaction();
            productions = materialProductionRepository.findAll().stream()
                    .map(materialsProductionMapper::mapFrom).collect(Collectors.toList());
            session.getTransaction().commit();
            return productions;
        }
    }

    public List<MaterialsProductionDto> findAllByBrigadeId(Integer id) {
        try (session) {
            List<MaterialsProductionDto> productions;
            session.beginTransaction();
            productions = materialProductionRepository.findAllByBrigadeId(id).stream()
                    .map(materialsProductionMapper::mapFrom).collect(Collectors.toList());
            session.getTransaction().commit();
            return productions;
        }
    }

    public Integer create(CreateMaterialsProductionDto materialsProductionDto) {
        try (session) {
            var validationResult = createMaterialsProductionValidator.isValid(materialsProductionDto);
            if (!validationResult.isValid()) {
                throw new ValidationException(validationResult.getErrors());
            }
            session.beginTransaction();
            var productionEntity = createMaterialsProductionMapper.mapFrom(materialsProductionDto);
            productionEntity = materialProductionRepository.save(productionEntity);
            session.getTransaction().commit();
            return productionEntity.getId();
        }
    }

    public boolean delete(Integer id) {
        try (session) {
            Optional<MaterialsProduction> maybe;
            session.beginTransaction();
            maybe = materialProductionRepository.findById(id);
            maybe.ifPresent(it -> materialProductionRepository.delete(id));
            session.getTransaction().commit();
            return maybe.isPresent();
        }
    }

    public void update(UpdateMaterialsProductionDto productionDto) {

        try (session) {
            var validationResult = updateMaterialsProductionValidator.isValid(productionDto);
            if (!validationResult.isValid()) {
                throw new ValidationException(validationResult.getErrors());
            }
            session.beginTransaction();
            var productionEntity = updateMaterialsProductionMapper.mapFrom(productionDto);
            materialProductionRepository.update(productionEntity);
            session.getTransaction().commit();
        }
    }
    public static MaterialsProductionService getInstance() {
        return INSTANCE;
    }
}
