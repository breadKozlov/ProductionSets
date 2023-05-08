package by.kozlov.hibernate.starter.validator;

import by.kozlov.hibernate.starter.dto.UpdateRequirementDto;
import by.kozlov.hibernate.starter.service.MaterialService;
import by.kozlov.hibernate.starter.service.SetService;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UpdateRequirementValidator implements Validator<UpdateRequirementDto> {

    private static final UpdateRequirementValidator INSTANCE = new UpdateRequirementValidator();

    @Override
    public ValidationResult isValid(UpdateRequirementDto object) {

        var validationResult = new ValidationResult();
        if (object.getTotalSets().isEmpty()) {
            validationResult.add(Error.of("invalid.totalSets","Total of sets is invalid"));
        }
        return validationResult;
    }

    public static UpdateRequirementValidator getInstance() {
        return INSTANCE;
    }
}
