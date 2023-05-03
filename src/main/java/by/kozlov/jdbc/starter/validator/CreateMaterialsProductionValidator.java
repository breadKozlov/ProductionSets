package by.kozlov.jdbc.starter.validator;

import by.kozlov.jdbc.starter.dto.CreateMaterialsProductionDto;
import by.kozlov.jdbc.starter.service.BrigadeService;
import by.kozlov.jdbc.starter.service.MaterialService;
import by.kozlov.jdbc.starter.utils.LocalDateFormatter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateMaterialsProductionValidator implements Validator<CreateMaterialsProductionDto>{


    private static final CreateMaterialsProductionValidator INSTANCE = new CreateMaterialsProductionValidator();
    private final BrigadeService brigadeService = BrigadeService.getInstance();
    private final MaterialService materialService = MaterialService.getInstance();
    @Override
    public ValidationResult isValid(CreateMaterialsProductionDto object) {

        var validationResult = new ValidationResult();
        if (!LocalDateFormatter.isValid(object.getDateOfProduction())) {
            validationResult.add(Error.of("invalid.dateOfProduction", "Date of production is invalid"));
        }
        if (brigadeService.find(object.getBrigade()).isEmpty()) {
            validationResult.add(Error.of("invalid.set", "Brigade is invalid"));
        }
        if (materialService.find(object.getMaterial()).isEmpty()) {
            validationResult.add(Error.of("invalid.material", "Material is invalid"));
        }
        if (object.getQuantity().isEmpty()) {
            validationResult.add(Error.of("invalid.quantity","Quantity is invalid"));
        }
        return validationResult;
    }

    public static CreateMaterialsProductionValidator getInstance() {
        return INSTANCE;
    }
}
