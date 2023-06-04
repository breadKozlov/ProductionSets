package by.kozlov.integration.service;

import by.kozlov.TestApplicationRunner;
import by.kozlov.spring.ApplicationRunner;
import by.kozlov.spring.service.ProductionService;
import by.kozlov.utils.TestDataImporter;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(classes = {TestApplicationRunner.class, ApplicationRunner.class})
@Transactional
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class ProductionServiceIT {
    private static final Integer PRODUCTION_ID = 46;
    @Autowired
    private ProductionService productionService;

    @Test
    void findById() {

        var productionDto = productionService.findById(PRODUCTION_ID);
        assertTrue(productionDto.isPresent());
        productionDto.ifPresent(p -> assertEquals("2023-05-05", p.getDateOfProduction().toString()));
    }


}
