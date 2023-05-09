package by.kozlov.hibernate.starter.validator;

import by.kozlov.hibernate.starter.dto.UpdateMaterialsProductionDto;
import by.kozlov.hibernate.starter.service.BrigadeService;
import by.kozlov.hibernate.starter.service.MaterialService;
import by.kozlov.hibernate.starter.utils.LocalDateFormatter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateMaterialsProductionValidator implements Validator<UpdateMaterialsProductionDto> {

    private static final UpdateMaterialsProductionValidator INSTANCE = new UpdateMaterialsProductionValidator();
    private final MaterialService materialService = MaterialService.getInstance();
    private final BrigadeService brigadeService = BrigadeService.getInstance();
    @Override
    public ValidationResult isValid(UpdateMaterialsProductionDto object) {
        var validationResult = new ValidationResult();
        if (!LocalDateFormatter.isValid(object.getDateOfProduction())) {
            validationResult.add(Error.of("invalid.dateOfProduction", "Date of production is invalid"));
        }
        if (materialService.find(object.getMaterial()).isEmpty()) {
            validationResult.add(Error.of("invalid.material", "Material is invalid"));
        }
        if (brigadeService.find(object.getBrigade()).isEmpty()) {
            validationResult.add(Error.of("invalid.brigade", "Brigade is invalid"));
        }
        if (object.getQuantity().isEmpty()) {
            validationResult.add(Error.of("invalid.quantity","Quantity is invalid"));
        }
        return validationResult;
    }

    public static UpdateMaterialsProductionValidator getInstance() {
        return INSTANCE;
    }
}
