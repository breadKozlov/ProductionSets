package by.kozlov.hibernate.starter.validator;

import by.kozlov.hibernate.starter.dto.CreateProductionDto;
import by.kozlov.hibernate.starter.service.SetService;
import by.kozlov.hibernate.starter.service.WorkerService;
import by.kozlov.hibernate.starter.utils.LocalDateFormatter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CreateProductionValidator implements Validator<CreateProductionDto> {

    private static final CreateProductionValidator INSTANCE = new CreateProductionValidator();
    private final SetService setService = SetService.getInstance();
    private final WorkerService workerService = WorkerService.getInstance();
    @Override
    public ValidationResult isValid(CreateProductionDto object) {
        var validationResult = new ValidationResult();
        if (!LocalDateFormatter.isValid(object.getDateOfProduction())) {
            validationResult.add(Error.of("invalid.dateOfProduction", "Date of production is invalid"));
        }
        if (setService.find(object.getSet()).isEmpty()) {
            validationResult.add(Error.of("invalid.set", "Set is invalid"));
        }
        if (workerService.find(object.getWorker()).isEmpty()) {
            validationResult.add(Error.of("invalid.worker", "Worker is invalid"));
        }
        if (object.getMadeSets().isEmpty()) {
            validationResult.add(Error.of("invalid.madeSets","Made sets is invalid"));
        }
        return validationResult;
    }

    public static CreateProductionValidator getInstance() {
        return INSTANCE;
    }
}
