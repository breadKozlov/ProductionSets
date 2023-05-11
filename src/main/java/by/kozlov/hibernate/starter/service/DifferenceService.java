package by.kozlov.hibernate.starter.service;

import by.kozlov.hibernate.starter.dto.DifferenceDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DifferenceService {

    private static final DifferenceService INSTANCE = new DifferenceService();

    private final RequirementService requirementService = RequirementService.getInstance();

    public List<DifferenceDto> findAllDifferenceProductionMaterials() {
        var objects = requirementService.findSumReqMaterials();
        List<DifferenceDto> list = new ArrayList<>();
        var materials = objects.stream().map(array ->
                Arrays.stream(array).map(Optional::ofNullable).collect(Collectors.toList())).toList();

        for(var material: materials) {
            var name = String.valueOf(material.get(0).orElse("No name"));
            var req = String.valueOf(material.get(1).orElse("0.0"));
            var rel = String.valueOf(material.get(2).orElse("0.0"));
            list.add(new DifferenceDto(name,Double.valueOf(req),
                    Double.valueOf(rel)));
        }
        return list;
    }

    //public List<DifferenceDto> findAllDifferenceProductionSets()
    public static DifferenceService getInstance() {
        return INSTANCE;
    }

}
