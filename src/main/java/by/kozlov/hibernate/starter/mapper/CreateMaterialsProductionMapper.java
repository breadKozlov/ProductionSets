package by.kozlov.hibernate.starter.mapper;

import by.kozlov.hibernate.starter.dao.BrigadeDao;
import by.kozlov.hibernate.starter.dao.MaterialDao;
import by.kozlov.hibernate.starter.entity.MaterialsProduction;
import by.kozlov.hibernate.starter.utils.LocalDateFormatter;
import by.kozlov.hibernate.starter.dto.CreateMaterialsProductionDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateMaterialsProductionMapper implements Mapper<CreateMaterialsProductionDto, MaterialsProduction>{

    private static final CreateMaterialsProductionMapper INSTANCE = new CreateMaterialsProductionMapper();

    private final MaterialDao materialDao = MaterialDao.getInstance();
    private final BrigadeDao brigadeDao = BrigadeDao.getInstance();

    @Override
    public MaterialsProduction mapFrom(CreateMaterialsProductionDto object) {

        return MaterialsProduction.builder()
                .material(materialDao.findById(Integer.parseInt(object.getMaterial())).orElseThrow())
                .brigade(brigadeDao.findById(Integer.parseInt(object.getBrigade())).orElseThrow())
                .quantity(Double.parseDouble(object.getQuantity()))
                .dateOfProduction(LocalDateFormatter.format(object.getDateOfProduction()))
                .build();
    }

    public static CreateMaterialsProductionMapper getInstance() {
        return INSTANCE;
    }
}
