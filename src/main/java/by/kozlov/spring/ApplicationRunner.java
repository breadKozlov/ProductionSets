package by.kozlov.spring;

import by.kozlov.spring.service.BrigadeService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ApplicationRunner {
    public static void main(String[] args) {
        var context = SpringApplication.run(ApplicationRunner.class,args);
        var brigadeService = context.getBean(BrigadeService.class);
        System.out.println(brigadeService.findAll());
    }
}
