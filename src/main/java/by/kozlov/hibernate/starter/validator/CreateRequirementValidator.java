package by.kozlov.hibernate.starter.validator;

import by.kozlov.hibernate.starter.dto.CreateRequirementDto;
import by.kozlov.hibernate.starter.service.MaterialService;
import by.kozlov.hibernate.starter.service.SetService;
public class CreateRequirementValidator implements Validator<CreateRequirementDto>{

    private static final CreateRequirementValidator INSTANCE = new CreateRequirementValidator();
    private final SetService setService = SetService.getInstance();
    private final MaterialService materialService = MaterialService.getInstance();
    @Override
    public ValidationResult isValid(CreateRequirementDto object) {
        var validationResult = new ValidationResult();

        if (setService.find(object.getSet()).isEmpty()) {
            validationResult.add(Error.of("invalid.set", "Set is invalid"));
        }
        if (materialService.find(object.getMaterial()).isEmpty()) {
            validationResult.add(Error.of("invalid.material", "Material is invalid"));
        }
        if (object.getUnitCost().isEmpty()) {
            validationResult.add(Error.of("invalid.unitCost","Cost of unit is invalid"));
        }
        if (object.getTotalSets().isEmpty()) {
            validationResult.add(Error.of("invalid.totalSets","Total of sets is invalid"));
        }

        return validationResult;
    }

    public static CreateRequirementValidator getInstance() {
        return INSTANCE;
    }
}
