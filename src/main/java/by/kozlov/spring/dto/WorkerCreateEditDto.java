package by.kozlov.spring.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class WorkerCreateEditDto {

    @NotEmpty
    String nameOfWorker;
    @NotEmpty
    String surnameOfWorker;
    @NotEmpty
    String speciality;
    @NotNull
    Integer rank;
    @NotNull
    Integer experience;
    @NotNull
    Integer brigadeId;
    String email;
}
