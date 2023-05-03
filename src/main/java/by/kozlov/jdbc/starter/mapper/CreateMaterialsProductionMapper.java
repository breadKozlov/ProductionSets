package by.kozlov.jdbc.starter.mapper;

import by.kozlov.jdbc.starter.dao.BrigadeDao;
import by.kozlov.jdbc.starter.dao.MaterialDao;
import by.kozlov.jdbc.starter.dto.CreateMaterialsProductionDto;
import by.kozlov.jdbc.starter.entity.MaterialsProduction;
import by.kozlov.jdbc.starter.utils.LocalDateFormatter;
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
