package by.kozlov.jdbc.starter.validator;

import by.kozlov.jdbc.starter.dto.UpdateRequirementDto;
import by.kozlov.jdbc.starter.service.MaterialService;
import by.kozlov.jdbc.starter.service.SetService;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UpdateRequirementValidator implements Validator<UpdateRequirementDto> {

    private static final UpdateRequirementValidator INSTANCE = new UpdateRequirementValidator();
    private final SetService setService = SetService.getInstance();
    private final MaterialService materialService = MaterialService.getInstance();

    @Override
    public ValidationResult isValid(UpdateRequirementDto object) {

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

    public static UpdateRequirementValidator getInstance() {
        return INSTANCE;
    }
}
