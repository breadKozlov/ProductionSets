package by.kozlov.integration.service;

import by.kozlov.TestApplicationRunner;
import by.kozlov.annotation.IT;
import by.kozlov.spring.ApplicationRunner;
import by.kozlov.spring.service.ProductionService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@IT
@RequiredArgsConstructor
public class ProductionServiceIT {
    private static final Integer PRODUCTION_ID = 3;
    private static final Integer WORKER_ID = 1;

    private final ProductionService productionService;

    @Test
    void findById() {

        var productionDto = productionService.findById(PRODUCTION_ID);
        assertTrue(productionDto.isPresent());
        productionDto.ifPresent(p -> assertEquals("2023-04-05", p.getDateOfProduction().toString()));
    }

    @Test
    void findAllByWorkerIdAndSort() {
        var sort = Sort.by("madeSets");
        var products = productionService.findAllByWorkerId(WORKER_ID,sort);
        assertThat(products).hasSize(3);

        var nameOfSets = products.stream().map(it -> it.getSet().getNameOfSet()).toList();
        assertThat(nameOfSets).contains("Set 54327-5000130", "Set 6501-5000120", "Set 6430-5000120");
    }


}
