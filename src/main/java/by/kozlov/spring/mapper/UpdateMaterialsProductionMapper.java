package by.kozlov.spring.mapper;

import by.kozlov.spring.repository.BrigadeRepository;
import by.kozlov.spring.repository.MaterialRepository;
import by.kozlov.spring.utils.LocalDateFormatter;
import by.kozlov.spring.dto.UpdateMaterialsProductionDto;
import by.kozlov.spring.entity.MaterialsProduction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateMaterialsProductionMapper implements Mapper<UpdateMaterialsProductionDto, MaterialsProduction> {

    private final MaterialRepository materialRepository;
    private final BrigadeRepository brigadeRepository;

    @Autowired
    public UpdateMaterialsProductionMapper(MaterialRepository materialRepository, BrigadeRepository brigadeRepository) {
        this.materialRepository = materialRepository;
        this.brigadeRepository = brigadeRepository;
    }

    @Override
    public MaterialsProduction mapFrom(UpdateMaterialsProductionDto object) {

        return MaterialsProduction.builder()
                .id(Integer.parseInt(object.getId()))
                .material(materialRepository.findById(Integer.parseInt(object.getMaterial())).orElseThrow())
                .brigade(brigadeRepository.findById(Integer.parseInt(object.getBrigade())).orElseThrow())
                .quantity(Double.parseDouble(object.getQuantity()))
                .dateOfProduction(LocalDateFormatter.format(object.getDateOfProduction()))
                .build();
    }
}