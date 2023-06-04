package by.kozlov.spring.mapper;

import by.kozlov.spring.dto.CreateMaterialsProductionDto;
import by.kozlov.spring.entity.MaterialsProduction;
import by.kozlov.spring.repository.BrigadeRepository;
import by.kozlov.spring.repository.MaterialRepository;
import by.kozlov.spring.utils.LocalDateFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateMaterialsProductionMapper implements Mapper<CreateMaterialsProductionDto, MaterialsProduction>{

    private final MaterialRepository materialRepository;
    private final BrigadeRepository brigadeRepository;

    @Autowired
    public CreateMaterialsProductionMapper(MaterialRepository materialRepository, BrigadeRepository brigadeRepository) {
        this.materialRepository = materialRepository;
        this.brigadeRepository = brigadeRepository;
    }

    @Override
    public MaterialsProduction mapFrom(CreateMaterialsProductionDto object) {

        return MaterialsProduction.builder()
                .material(materialRepository.findById(Integer.parseInt(object.getMaterial())).orElseThrow())
                .brigade(brigadeRepository.findById(Integer.parseInt(object.getBrigade())).orElseThrow())
                .quantity(Double.parseDouble(object.getQuantity()))
                .dateOfProduction(LocalDateFormatter.format(object.getDateOfProduction()))
                .build();
    }
}
