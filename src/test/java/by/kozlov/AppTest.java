package by.kozlov;

import by.kozlov.hibernate.starter.dto.CreateProductionDto;
import by.kozlov.hibernate.starter.dto.UpdateProductionDto;
import by.kozlov.hibernate.starter.dto.UpdateRequirementDto;
import by.kozlov.hibernate.starter.entity.Worker;
import by.kozlov.hibernate.starter.mapper.UpdateRequirementMapper;
import by.kozlov.hibernate.starter.service.ProductionService;
import by.kozlov.hibernate.starter.service.RequirementService;
import by.kozlov.hibernate.starter.service.SetService;
import by.kozlov.hibernate.starter.service.WorkerService;
import by.kozlov.hibernate.starter.utils.HibernateUtil;
import lombok.Cleanup;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;

public class AppTest
{

    @Test
    public void findAllRequirement() {
        var req = RequirementService.getInstance();
        System.out.println(req.findAll());
    }

    @Test
    public void findSum() {
        var req = RequirementService.getInstance();
        var arrays = req.findSum();
        for (Object[] array: arrays) {
            for (Object obj: array) {
                System.out.print(obj.toString() + " - ");
            }
            System.out.println();
        }
    }
    /*
    @Test
    public void findAllWorkers() {

        @Cleanup var sessionFactory = HibernateUtil.getConfig().buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();

        session.beginTransaction();

        var workers = session.createQuery("FROM Worker", Worker.class)
                .list();

        System.out.println(workers);

        session.getTransaction().commit();

    }

    @Test
    public void findAllWorkersService() {
        var workersService = WorkerService.getInstance();
        System.out.println(workersService.findAll());
    }

    @Test
    public void findAllProductions() {
        var productionsService = ProductionService.getInstance();
        System.out.println(productionsService.findAll());
    }

    @Test
    public void findByIdProductions() {
        var productionService = ProductionService.getInstance();
        System.out.println(productionService.findById(1));
    }

    @Test
    public void saveProduction() {
        var productionService = ProductionService.getInstance();

        var newProduct = CreateProductionDto.builder().worker("1").set("12").madeSets("18")
                .dateOfProduction("2023-05-05").build();
        System.out.println(productionService.create(newProduct));
    }

    @Test
    public void deleteProduction() {
        var productionService = ProductionService.getInstance();
        System.out.println(productionService.delete(36));
    }

    @Test
    public void findAllByWorkerId() {
        var productionService = ProductionService.getInstance();
        System.out.println(productionService.findAllByWorkerId(1));
    }

    @Test
    public void updateProduction() {
        var productionService = ProductionService.getInstance();
        var updateProduct = UpdateProductionDto.builder().id("37").worker("2").set("3")
                .dateOfProduction("2023-05-09").madeSets("1000").build();
        System.out.println(productionService.update(updateProduct));
    }
    */
}
