package by.kozlov.spring.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class WorkerCreateEditDto {

    @NotEmpty(message = "Name is not be empty")
    String nameOfWorker;
    @NotEmpty(message = "Surname is not be empty")
    String surnameOfWorker;
    @NotEmpty(message = "Speciality is not be empty")
    String speciality;
    @NotNull(message = "Rank is not be empty")
    Integer rank;
    @NotNull(message = "Experience is not be empty")
    Integer experience;
    @NotNull(message = "Brigade is not be empty")
    Integer brigadeId;
    String email;
}
