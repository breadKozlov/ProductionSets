package by.kozlov.spring.mapper;

import by.kozlov.spring.database.entity.Brigade;
import by.kozlov.spring.database.entity.MaterialsProduction;
import by.kozlov.spring.database.repository.BrigadeRepository;
import by.kozlov.spring.database.repository.MaterialRepository;
import by.kozlov.spring.database.entity.Material;
import by.kozlov.spring.dto.MaterialsProductionCreateEditDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MaterialsProductionCreateEditMapper implements Mapper<MaterialsProductionCreateEditDto,MaterialsProduction> {

    private final MaterialRepository materialRepository;
    private final BrigadeRepository brigadeRepository;

    @Override
    public MaterialsProduction map(MaterialsProductionCreateEditDto object) {
        MaterialsProduction materialsProduction = new MaterialsProduction();
        copy(object,materialsProduction);
        return materialsProduction;
    }

    @Override
    public MaterialsProduction map(MaterialsProductionCreateEditDto fromObject, MaterialsProduction toObject) {
        copy(fromObject,toObject);
        return toObject;
    }

    @Override
    public void copy(MaterialsProductionCreateEditDto object, MaterialsProduction materialsProduction) {
        materialsProduction.setMaterial(getMaterial(object.getMaterialId()));
        materialsProduction.setBrigade(getBrigade(object.getBrigadeId()));
        materialsProduction.setQuantity(object.getQuantity());
        materialsProduction.setDateOfProduction(object.getDateOfProduction());
    }

    private Material getMaterial(Integer materialId) {
        return  Optional.ofNullable(materialId)
                .flatMap(materialRepository::findById)
                .orElse(null);
    }

    private Brigade getBrigade(Integer brigadeId) {
        return Optional.ofNullable(brigadeId)
                .flatMap(brigadeRepository::findById)
                .orElse(null);
    }

}
