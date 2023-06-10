package by.kozlov.spring;

import by.kozlov.spring.dto.CreateMaterialsProductionDto;
import by.kozlov.spring.dto.UpdateMaterialsProductionDto;
import by.kozlov.spring.entity.MaterialsProduction;
import by.kozlov.spring.service.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ApplicationRunner {
    public static void main(String[] args) {
        var context = SpringApplication.run(ApplicationRunner.class,args);
        var materialsProductionService = context.getBean(MaterialsProductionService.class);
        var page = PageRequest.of(0,3, Sort.by("dateOfProduction"));
        System.out.println(materialsProductionService.findAll(page));
    }
}
