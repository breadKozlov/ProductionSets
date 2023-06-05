package by.kozlov.spring;

import by.kozlov.spring.dto.CreateMaterialsProductionDto;
import by.kozlov.spring.dto.UpdateMaterialsProductionDto;
import by.kozlov.spring.entity.MaterialsProduction;
import by.kozlov.spring.service.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ApplicationRunner {
    public static void main(String[] args) {
        var context = SpringApplication.run(ApplicationRunner.class,args);
        var productionService = context.getBean(ProductionService.class);
        var requirementService = context.getBean(RequirementService.class);
        var userService = context.getBean(UserService.class);
        var workersSetsService = context.getBean(WorkersSetsService.class);
        var diffService = context.getBean(DifferenceService.class);
        System.out.println(productionService.findAll());
        System.out.println(requirementService.findAll());
        System.out.println(userService.login("saf","asdf"));
        System.out.println(workersSetsService.findAll());
        System.out.println(diffService.findAllDifferenceProductionMaterials());
    }
}
