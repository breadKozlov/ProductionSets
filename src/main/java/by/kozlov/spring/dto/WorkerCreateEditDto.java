package by.kozlov.spring.dto;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class WorkerCreateEditDto {

    String nameOfWorker;
    String surnameOfWorker;
    String speciality;
    Integer rank;
    Integer experience;
    Integer brigadeId;
    String email;
}
