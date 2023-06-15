package by.kozlov.integration.service;

import by.kozlov.TestApplicationRunner;
import by.kozlov.annotation.IT;
import by.kozlov.spring.ApplicationRunner;
import by.kozlov.spring.dto.DifferenceDto;
import by.kozlov.spring.service.DifferenceService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@IT
@RequiredArgsConstructor
public class DifferenceServiceIT {

    private final DifferenceService differenceService;

    @Test
    void findAllDifferenceProductionMaterials() {

        List<DifferenceDto> productionMaterials = differenceService.findAllDifferenceProductionMaterials();
        assertThat(productionMaterials).hasSize(6);

        List<Double> difference = productionMaterials.stream().map(it -> Math.ceil(it.difference()))
                .collect(Collectors.toList());
        assertThat(difference).contains(-288.0, 473.0, 878.0, 15729.0, -11.0, -1815.0);
    }
}
