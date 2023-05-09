package by.kozlov.hibernate.starter.validator;

import by.kozlov.hibernate.starter.dto.UpdateWorkersSetsDto;
import by.kozlov.hibernate.starter.service.SetService;
import by.kozlov.hibernate.starter.service.WorkerService;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateWorkersSetsValidator implements Validator<UpdateWorkersSetsDto>{
    private final SetService setService = SetService.getInstance();
    private final WorkerService workerService = WorkerService.getInstance();

    private static final UpdateWorkersSetsValidator INSTANCE = new UpdateWorkersSetsValidator();
    @Override
    public ValidationResult isValid(UpdateWorkersSetsDto object) {

        var validationResult = new ValidationResult();

        if (setService.find(object.getSet()).isEmpty()) {
            validationResult.add(Error.of("invalid.set", "Set is invalid"));
        }
        if (workerService.find(object.getWorker()).isEmpty()) {
            validationResult.add(Error.of("invalid.worker", "Worker is invalid"));
        }
        if (object.getRequirement().isEmpty()) {
            validationResult.add(Error.of("invalid.requirement","Requirement is invalid"));
        }
        return validationResult;
    }

    public static UpdateWorkersSetsValidator getInstance(){
        return INSTANCE;
    }
}
