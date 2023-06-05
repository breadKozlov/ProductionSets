package by.kozlov.integration.database;

import by.kozlov.TestApplicationRunner;
import by.kozlov.spring.ApplicationRunner;
import by.kozlov.spring.entity.Production;
import by.kozlov.spring.repository.ProductionRepository;
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
public class ProductionRepositoryTest {

    @Autowired
    private ProductionRepository productionRepository;

    @Test

    void findAll() {

        List<Production> results = productionRepository.findAll();
        assertThat(results).hasSize(4);

        List<String> fullNames = results.stream().map(Production::fullName).collect(Collectors.toList());
        assertThat(fullNames).containsExactlyInAnyOrder("Alexei Bratchenya Set 6430-5000120 250",
                "Alexei Bratchenya Set 54327-5000130 100",
                "Alexei Bratchenya Set 6501-5000120 150",
                "Alexander Ovchinnikov Set 54327-5000130 100");
    }

}
