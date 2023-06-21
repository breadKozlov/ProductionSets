package by.kozlov.integration.service;

import by.kozlov.annotation.IT;
import by.kozlov.spring.service.MaterialsProductionService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import by.kozlov.spring.dto.MaterialsProductionReadDto;

import static org.assertj.core.api.Assertions.assertThat;

@IT
@RequiredArgsConstructor
public class MaterialsProductionIT {

    private static final Integer BRIGADE_ID = 1;

    private final MaterialsProductionService materialsProductionService;

    @Test
    void findAllByPageAndSortByDateOfProduction() {

        var page = PageRequest.of(0,
                4,
                Sort.by("dateOfProduction"));
        var production = materialsProductionService.findAll(page);

        assertThat(production).hasSize(4);

        var datesOfProduction = production.stream().map(it -> it.getDateOfProduction().toString()).toList();
        assertThat(datesOfProduction).contains("2023-01-03", "2023-01-05", "2023-02-06", "2023-03-02");
    }

    @Test
    void findAllByBrigadeIdAndByPageAndSortByDateOfProduction() {

        var page = PageRequest.of(2,2,Sort.by("dateOfProduction").descending());
        var production = materialsProductionService.findAllByBrigadeId(BRIGADE_ID,page);

        assertThat(production).hasSize(1);
        var quantities = production.stream().map(MaterialsProductionReadDto::getQuantity).toList();
        assertThat(quantities).contains(54.0);
    }
}
