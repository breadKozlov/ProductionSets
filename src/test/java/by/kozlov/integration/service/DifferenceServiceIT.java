package by.kozlov.integration.service;

import by.kozlov.TestApplicationRunner;
import by.kozlov.spring.ApplicationRunner;
import by.kozlov.spring.dto.DifferenceDto;
import by.kozlov.spring.service.DifferenceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {TestApplicationRunner.class, ApplicationRunner.class})
@Transactional
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@Sql({"/init.sql","/test.sql"})
public class DifferenceServiceIT {

    @Autowired
    private DifferenceService differenceService;

    @Test
    void findAllDifferenceProductionMaterials() {

        List<DifferenceDto> productionMaterials = differenceService.findAllDifferenceProductionMaterials();
        assertThat(productionMaterials).hasSize(6);

        List<Double> difference = productionMaterials.stream().map(it -> Math.ceil(it.difference()))
                .collect(Collectors.toList());
        assertThat(difference).contains(-383.0, -1178.0, 878.0, 15713.0, -538.0, -1815.0);

    }

}
