package by.kozlov.hibernate.starter.service;

import by.kozlov.hibernate.starter.dao.BrigadeRepository;
import by.kozlov.hibernate.starter.dao.MaterialProductionRepository;
import by.kozlov.hibernate.starter.dao.MaterialRepository;
import by.kozlov.hibernate.starter.dto.CreateMaterialsProductionDto;
import by.kozlov.hibernate.starter.dto.MaterialsProductionDto;
import by.kozlov.hibernate.starter.dto.UpdateMaterialsProductionDto;
import by.kozlov.hibernate.starter.entity.MaterialsProduction;
import by.kozlov.hibernate.starter.mapper.*;
import by.kozlov.hibernate.starter.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MaterialsProductionService {

    private static final MaterialsProductionService INSTANCE = new MaterialsProductionService();

    private final MaterialProductionRepository materialProductionRepository;

    private final MaterialsProductionMapper materialsProductionMapper;

    private final CreateMaterialsProductionMapper createMaterialsProductionMapper;

    private final UpdateMaterialsProductionMapper updateMaterialsProductionMapper;

    private final Session session;

    private MaterialsProductionService() {
        SessionFactory sessionFactory = HibernateUtil.getConfig().buildSessionFactory();
        session = HibernateUtil.getProxySession(sessionFactory);
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
        try (session;
             var validationFactory = Validation.buildDefaultValidatorFactory()) {

            var validator = validationFactory.getValidator();
            var validationResult = validator.validate(materialsProductionDto);
            if (!validationResult.isEmpty()) {
                throw new ConstraintViolationException(validationResult);
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

        try (session;
             var validationFactory = Validation.buildDefaultValidatorFactory()) {

            var validator = validationFactory.getValidator();
            var validationResult = validator.validate(productionDto);
            if (!validationResult.isEmpty()) {
                throw new ConstraintViolationException(validationResult);
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
