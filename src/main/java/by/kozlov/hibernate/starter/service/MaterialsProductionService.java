package by.kozlov.hibernate.starter.service;

import by.kozlov.hibernate.starter.dto.*;
import by.kozlov.hibernate.starter.exception.ValidationException;
import by.kozlov.hibernate.starter.dao.MaterialsProductionDao;
import by.kozlov.hibernate.starter.mapper.CreateMaterialsProductionMapper;
import by.kozlov.hibernate.starter.mapper.MaterialsProductionMapper;
import by.kozlov.hibernate.starter.mapper.UpdateMaterialsProductionMapper;
import by.kozlov.hibernate.starter.utils.HibernateUtil;
import by.kozlov.hibernate.starter.validator.CreateMaterialsProductionValidator;
import by.kozlov.hibernate.starter.validator.UpdateMaterialsProductionValidator;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.stream.Collectors;

public class MaterialsProductionService {

    private final SessionFactory sessionFactory = HibernateUtil.getConfig().buildSessionFactory();

    private static final MaterialsProductionService INSTANCE = new MaterialsProductionService();
    private final MaterialsProductionDao materialsProductionDao = MaterialsProductionDao.getInstance();
    private final MaterialsProductionMapper materialsProductionMapper = MaterialsProductionMapper.getInstance();

    private final CreateMaterialsProductionMapper createMaterialsProductionMapper = CreateMaterialsProductionMapper.getInstance();

    private final UpdateMaterialsProductionValidator updateMaterialsProductionValidator = UpdateMaterialsProductionValidator.getInstance();
    private final UpdateMaterialsProductionMapper updateMaterialsProductionMapper = UpdateMaterialsProductionMapper.getInstance();
    private final CreateMaterialsProductionValidator createMaterialsProductionValidator = CreateMaterialsProductionValidator.getInstance();
    public List<MaterialsProductionDto> findAll() {
        try (var session = sessionFactory.openSession()) {
            List<MaterialsProductionDto> productions;
            session.beginTransaction();
            productions = materialsProductionDao.findAll(session).stream()
                    .map(materialsProductionMapper::mapFrom).collect(Collectors.toList());
            session.getTransaction().commit();
            return productions;
        }
    }

    public List<MaterialsProductionDto> findAllByBrigadeId(Integer id) {
        try (var session = sessionFactory.openSession()) {
            List<MaterialsProductionDto> productions;
            session.beginTransaction();
            productions = materialsProductionDao.findAllByBrigadeId(session,id).stream()
                    .map(materialsProductionMapper::mapFrom).collect(Collectors.toList());
            session.getTransaction().commit();
            return productions;
        }
    }

    public Integer create(CreateMaterialsProductionDto materialsProductionDto) {
        try (var session = sessionFactory.openSession()) {
            var validationResult = createMaterialsProductionValidator.isValid(materialsProductionDto);
            if (!validationResult.isValid()) {
                throw new ValidationException(validationResult.getErrors());
            }
            var productionEntity = createMaterialsProductionMapper.mapFrom(materialsProductionDto);
            session.beginTransaction();
            productionEntity = materialsProductionDao.save(session,productionEntity);
            session.getTransaction().commit();
            return productionEntity.getId();
        }
    }

    public boolean delete(Integer id) {
        try (var session = sessionFactory.openSession()) {
            boolean result;
            session.beginTransaction();
            result = materialsProductionDao.delete(session,id);
            session.getTransaction().commit();
            return result;
        }
    }

    public boolean update(UpdateMaterialsProductionDto productionDto) {

        try (var session = sessionFactory.openSession()) {
            boolean result;
            var validationResult = updateMaterialsProductionValidator.isValid(productionDto);
            if (!validationResult.isValid()) {
                throw new ValidationException(validationResult.getErrors());
            }
            var productionEntity = updateMaterialsProductionMapper.mapFrom(productionDto);
            session.beginTransaction();
            result = materialsProductionDao.update(session, productionEntity);
            session.getTransaction().commit();
            return result;
        }
    }

    private MaterialsProductionService() {}

    public static MaterialsProductionService getInstance() {
        return INSTANCE;
    }
}
