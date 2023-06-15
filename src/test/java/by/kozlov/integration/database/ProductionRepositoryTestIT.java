package by.kozlov.integration.database;

import by.kozlov.annotation.IT;
import by.kozlov.spring.database.entity.Production;
import by.kozlov.spring.database.repository.ProductionRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;


@IT
@RequiredArgsConstructor
public class ProductionRepositoryTestIT {

    private final ProductionRepository productionRepository;

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
