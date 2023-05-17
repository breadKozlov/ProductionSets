package by.kozlov.hibernate.starter.mapper;

import by.kozlov.hibernate.starter.dao.BrigadeDao;
import by.kozlov.hibernate.starter.dao.BrigadeRepository;
import by.kozlov.hibernate.starter.dao.MaterialDao;
import by.kozlov.hibernate.starter.dao.MaterialRepository;
import by.kozlov.hibernate.starter.dto.UpdateMaterialsProductionDto;
import by.kozlov.hibernate.starter.entity.MaterialsProduction;
import by.kozlov.hibernate.starter.utils.HibernateUtil;
import by.kozlov.hibernate.starter.utils.LocalDateFormatter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;

@RequiredArgsConstructor
public class UpdateMaterialsProductionMapper implements Mapper<UpdateMaterialsProductionDto, MaterialsProduction> {

    private final MaterialRepository materialRepository;
    private final BrigadeRepository brigadeRepository;

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
