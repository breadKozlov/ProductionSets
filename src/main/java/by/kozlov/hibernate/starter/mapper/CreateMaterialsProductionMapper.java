package by.kozlov.hibernate.starter.mapper;

import by.kozlov.hibernate.starter.dao.*;
import by.kozlov.hibernate.starter.entity.Material;
import by.kozlov.hibernate.starter.entity.MaterialsProduction;
import by.kozlov.hibernate.starter.entity.Production;
import by.kozlov.hibernate.starter.utils.HibernateUtil;
import by.kozlov.hibernate.starter.utils.LocalDateFormatter;
import by.kozlov.hibernate.starter.dto.CreateMaterialsProductionDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;

@RequiredArgsConstructor
public class CreateMaterialsProductionMapper implements Mapper<CreateMaterialsProductionDto, MaterialsProduction>{

    private final MaterialRepository materialRepository;
    private final BrigadeRepository brigadeRepository;

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

