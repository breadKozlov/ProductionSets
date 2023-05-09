package by.kozlov.hibernate.starter.service;

import by.kozlov.hibernate.starter.dto.DifferenceMaterialsDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DifferenceMaterialsService {

    private static final DifferenceMaterialsService INSTANCE = new DifferenceMaterialsService();

    private final RequirementService requirementService = RequirementService.getInstance();
    private final MaterialsProductionService materialsProductionService = MaterialsProductionService.getInstance();

    public List<DifferenceMaterialsDto> findAllDifferenceMaterials() {
        var requiredMaterials = requirementService.findSumReqMaterials();
        var releasedMaterials = materialsProductionService.findSumRelMaterials();
        List<DifferenceMaterialsDto> list = new ArrayList<>();
        //сначала вставляет все имена материалов из таблицы
        for (var material: requiredMaterials) {
            var parameter = Optional.ofNullable(material[1]);
                list.add(DifferenceMaterialsDto.builder()
                                .nameOfMaterial((String) material[0])
                                        .requiredMaterial((Double) parameter.orElse(0.0))
                        .build());

        }

        //если имя из первой таблицы совпадает с именем из второй вставляет результат второй таблицы иначе 0
        for (var materialDto: list) {
            for (var material: releasedMaterials){
                    if (materialDto.getNameOfMaterial().equals(material[0])) {
                        materialDto.setReleasedMaterial((Double) material[1]);
                        break;
                    } else {
                        materialDto.setReleasedMaterial(0.0);
                    }
                }
            }
        return list;
    }
    public static DifferenceMaterialsService getInstance() {
        return INSTANCE;
    }

}
