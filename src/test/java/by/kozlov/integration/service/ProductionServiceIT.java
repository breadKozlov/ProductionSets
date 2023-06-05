package by.kozlov.integration.service;

import by.kozlov.TestApplicationRunner;
import by.kozlov.spring.ApplicationRunner;
import by.kozlov.spring.service.ProductionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(classes = {TestApplicationRunner.class, ApplicationRunner.class})
@Transactional
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@Sql({"/init.sql","/test.sql"})
public class ProductionServiceIT {
    private static final Integer PRODUCTION_ID = 3;
    @Autowired
    private ProductionService productionService;

    @Test
    void findById() {

        var productionDto = productionService.findById(PRODUCTION_ID);
        assertTrue(productionDto.isPresent());
        productionDto.ifPresent(p -> assertEquals("2023-04-05", p.getDateOfProduction().toString()));
    }


}
