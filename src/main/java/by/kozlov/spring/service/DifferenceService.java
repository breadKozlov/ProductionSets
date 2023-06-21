package by.kozlov.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import by.kozlov.spring.dto.DifferenceDto;
import java.util.Optional;
import java.util.stream.Collectors;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class DifferenceService {

    private final RequirementService requirementService;
    private final ProductionService productionService;
    private final WorkersSetsService workersSetsService;

    public List<DifferenceDto> findAllDifferenceProductionMaterials() {
        var objects = requirementService.findSumReqMaterials();
        List<DifferenceDto> list = new ArrayList<>();
        var materials = objects.stream().map(array ->
                Arrays.stream(array).map(Optional::ofNullable).collect(Collectors.toList())).toList();

        for(var material: materials ) {
            var name = String.valueOf(material.get(0).orElse("No name"));
            var req = String.valueOf(material.get(1).orElse("0.0"));
            var rel = String.valueOf(material.get(2).orElse("0.0"));
            list.add(new DifferenceDto(name,Double.valueOf(req),
                    Double.valueOf(rel)));
        }
        return list;
    }

    public List<DifferenceDto> findAllDifferenceProductionSets() {

        return productionService.findSumReqMaterials().stream().map(array ->
                        Arrays.stream(array).map(Optional::ofNullable).collect(Collectors.toList())).toList()
                .stream().map(product -> {
                    var name = String.valueOf(product.get(0).orElse("No name"));
                    var req = String.valueOf(product.get(1).orElse("0.0"));
                    var rel = String.valueOf(product.get(2).orElse("0.0"));
                    return new DifferenceDto(name,Double.valueOf(req),Double.valueOf(rel));
                }).collect(Collectors.toList());
    }

    public List<DifferenceDto> findAllDifferenceWorkerSets(Integer id) {

        return workersSetsService.findAllProdSetsById(id).stream().map(array ->
                        Arrays.stream(array).map(Optional::ofNullable).collect(Collectors.toList())).toList()
                .stream().map(it -> {
                    var name = String.valueOf(it.get(0).orElse("No name"));
                    var req = String.valueOf(it.get(1).orElse("0.0"));
                    var rel = String.valueOf(it.get(2).orElse("0.0"));
                    return new DifferenceDto(name,Double.valueOf(req),Double.valueOf(rel));
                }).collect(Collectors.toList());
    }
}
